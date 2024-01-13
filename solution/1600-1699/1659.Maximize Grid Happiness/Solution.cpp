class Solution {
public:
    int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int mx = pow(3, n);
        int f[mx];
        int g[mx][mx];
        int bits[mx][n];
        int ix[mx];
        int ex[mx];
        int memo[m][mx][introvertsCount + 1][extrovertsCount + 1];
        int h[3][3] = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
        memset(f, 0, sizeof(f));
        memset(g, 0, sizeof(g));
        memset(bits, 0, sizeof(bits));
        memset(ix, 0, sizeof(ix));
        memset(ex, 0, sizeof(ex));
        memset(memo, -1, sizeof(memo));
        for (int i = 0; i < mx; ++i) {
            int mask = i;
            for (int j = 0; j < n; ++j) {
                int x = mask % 3;
                mask /= 3;
                bits[i][j] = x;
                if (x == 1) {
                    ix[i]++;
                    f[i] += 120;
                } else if (x == 2) {
                    ex[i]++;
                    f[i] += 40;
                }
                if (j) {
                    f[i] += h[x][bits[i][j - 1]];
                }
            }
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < mx; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += h[bits[i][k]][bits[j][k]];
                }
            }
        }
        function<int(int, int, int, int)> dfs = [&](int i, int pre, int ic, int ec) {
            if (i == m || (ic == 0 && ec == 0)) {
                return 0;
            }
            if (memo[i][pre][ic][ec] != -1) {
                return memo[i][pre][ic][ec];
            }
            int ans = 0;
            for (int cur = 0; cur < mx; ++cur) {
                if (ix[cur] <= ic && ex[cur] <= ec) {
                    ans = max(ans, f[cur] + g[pre][cur] + dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]));
                }
            }
            return memo[i][pre][ic][ec] = ans;
        };
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }
};