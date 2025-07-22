class Solution {
public:
    int houseOfCards(int n) {
        int f[n + 1][n / 3 + 1];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int n, int k) -> int {
            int x = 3 * k + 2;
            if (x > n) {
                return 0;
            }
            if (x == n) {
                return 1;
            }
            if (f[n][k] != -1) {
                return f[n][k];
            }
            return f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
        };
        return dfs(n, 0);
    }
};
