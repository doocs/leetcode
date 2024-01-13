class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] <= '6')) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
};