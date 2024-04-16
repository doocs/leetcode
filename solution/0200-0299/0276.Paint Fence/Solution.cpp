class Solution {
public:
    int numWays(int n, int k) {
        vector<int> f(n);
        vector<int> g(n);
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
};