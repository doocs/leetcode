class Solution {
public:
    int numSquares(int n) {
        int m = sqrt(n);
        vector<int> f(n + 1, 1 << 30);
        f[0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = i * i; j <= n; ++j) {
                f[j] = min(f[j], f[j - i * i] + 1);
            }
        }
        return f[n];
    }
};
