class Solution {
public:
    int numMusicPlaylists(int n, int goal, int k) {
        const int mod = 1e9 + 7;
        vector<long long> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= goal; ++i) {
            vector<long long> g(n + 1);
            for (int j = 1; j <= n; ++j) {
                g[j] = f[j - 1] * (n - j + 1);
                if (j > k) {
                    g[j] += f[j] * (j - k);
                }
                g[j] %= mod;
            }
            f = move(g);
        }
        return f[n];
    }
};