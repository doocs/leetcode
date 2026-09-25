class Solution {
public:
    int shortestMatchingSubstring(string s, string p) {
        int star = p.find('*');
        int star2 = p.find('*', star + 1);
        string a = p.substr(0, star);
        string b = p.substr(star + 1, star2 - star - 1);
        string c = p.substr(star2 + 1);
        vector<int> A = starts(s, a), B = starts(s, b), C = starts(s, c);
        int ans = s.size() + 1;
        int j = 0, k = 0;
        for (int i : A) {
            while (j < (int) B.size() && B[j] < i + (int) a.size()) {
                ++j;
            }
            if (j == (int) B.size()) {
                break;
            }
            while (k < (int) C.size() && C[k] < B[j] + (int) b.size()) {
                ++k;
            }
            if (k == (int) C.size()) {
                break;
            }
            ans = min(ans, C[k] + (int) c.size() - i);
        }
        return ans > (int) s.size() ? -1 : ans;
    }

private:
    vector<int> starts(const string& s, const string& pat) {
        int n = s.size();
        if (pat.empty()) {
            vector<int> res(n + 1);
            iota(res.begin(), res.end(), 0);
            return res;
        }
        int m = pat.size();
        vector<int> lps(m);
        for (int i = 1, len = 0; i < m;) {
            if (pat[i] == pat[len]) {
                lps[i++] = ++len;
            } else if (len) {
                len = lps[len - 1];
            } else {
                ++i;
            }
        }
        vector<int> res;
        for (int i = 0, j = 0; i < n;) {
            if (s[i] == pat[j]) {
                ++i;
                ++j;
                if (j == m) {
                    res.push_back(i - m);
                    j = lps[j - 1];
                }
            } else if (j) {
                j = lps[j - 1];
            } else {
                ++i;
            }
        }
        return res;
    }
};
