class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        int n = s.length();
        int cnt = (n - count(s.begin(), s.end(), '-')) % k;
        if (cnt == 0) {
            cnt = k;
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (c == '-') {
                continue;
            }
            ans += toupper(c);
            if (--cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans += '-';
                }
            }
        }
        if (!ans.empty() && ans.back() == '-') {
            ans.pop_back();
        }
        return ans;
    }
};