class Solution {
public:
    int numWays(int steps, int arrLen) {
        int f[steps][steps + 1];
        memset(f, -1, sizeof f);
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i > j || i >= arrLen || i < 0 || j < 0) {
                return 0;
            }
            if (i == 0 && j == 0) {
                return 1;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 0;
            for (int k = -1; k <= 1; ++k) {
                ans = (ans + dfs(i + k, j - 1)) % mod;
            }
            return f[i][j] = ans;
        };
        return dfs(0, steps);
    }
};