class Solution {
public:
    int numberOfWays(int startPos, int endPos, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1][k + 1];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> int {
            if (i > j || j < 0) {
                return 0;
            }
            if (j == 0) {
                return i == 0 ? 1 : 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = (dfs(dfs, i + 1, j - 1) + dfs(dfs, abs(i - 1), j - 1)) % mod;
            return f[i][j];
        };
        return dfs(dfs, abs(startPos - endPos), k);
    }
};
