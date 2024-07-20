class Solution {
public:
    int integerBreak(int n) {
        vector<int> f(n + 1);
        f[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                f[i] = max({f[i], f[i - j] * j, (i - j) * j});
            }
        }
        return f[n];
    }
};