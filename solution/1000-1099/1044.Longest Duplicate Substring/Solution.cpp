typedef unsigned long long ULL;

class Solution {
public:
    ULL p[30010];
    ULL h[30010];
    string longestDupSubstring(string s) {
        int base = 131, n = s.size();
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s[i];
        }
        int left = 0, right = n;
        string ans = "";
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            string t = check(s, mid);
            if (t.empty())
                right = mid - 1;
            else {
                left = mid;
                ans = t;
            }
        }
        return ans;
    }

    string check(string& s, int len) {
        int n = s.size();
        unordered_set<ULL> vis;
        for (int i = 1; i + len - 1 <= n; ++i) {
            int j = i + len - 1;
            ULL t = h[j] - h[i - 1] * p[j - i + 1];
            if (vis.count(t)) return s.substr(i - 1, len);
            vis.insert(t);
        }
        return "";
    }
};