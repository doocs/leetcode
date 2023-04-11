class Solution {
public:
    int longestValidParentheses(string s) {
        int n = s.size();
        if (n < 2) {
            return 0;
        }
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
            }
        }
        return *max_element(f, f + n + 1);
    }
};