class Solution {
public:
    int findSubstringInWraproundString(string s) {
        int f[26]{};
        int n = s.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i && (s[i] - s[i - 1] + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            f[s[i] - 'a'] = max(f[s[i] - 'a'], k);
        }
        return accumulate(begin(f), end(f), 0);
    }
};