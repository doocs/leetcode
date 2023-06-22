class Solution {
public:
    int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int h[3][3] = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};
        int p = pow(3, n - 1);
        int memo[m * n][p * 3][introvertsCount + 1][extrovertsCount + 1];
        memset(memo, -1, sizeof(memo));
        function<int(int, int, int, int)> dfs = [&](int pos, int pre, int ic, int ec) {
            if (pos == m * n || (ic == 0 && ec == 0)) {
                return 0;
            }
            if (memo[pos][pre][ic][ec] != -1) {
                return memo[pos][pre][ic][ec];
            }
            int ans = 0;
            int up = pre / p;
            int left = pos % n == 0 ? 0 : pre % 3;
            for (int i = 0; i < 3; ++i) {
                if ((i == 1 && ic == 0) || (i == 2 && ec == 0)) {
                    continue;
                }
                int cur = pre % p * 3 + i;
                int a = h[up][i] + h[left][i];
                int b = dfs(pos + 1, cur, ic - (i == 1), ec - (i == 2));
                int c = i == 1 ? 120 : (i == 2 ? 40 : 0);
                ans = max(ans, a + b + c);
            }
            return memo[pos][pre][ic][ec] = ans;
        };
        return dfs(0, 0, introvertsCount, extrovertsCount);
    }
};