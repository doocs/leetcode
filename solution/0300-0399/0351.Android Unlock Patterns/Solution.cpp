class Solution {
public:
    int numberOfPatterns(int m, int n) {
        int cross[10][10];
        memset(cross, 0, sizeof(cross));
        bool vis[10];
        memset(vis, false, sizeof(vis));
        cross[1][3] = cross[3][1] = 2;
        cross[1][7] = cross[7][1] = 4;
        cross[1][9] = cross[9][1] = 5;
        cross[2][8] = cross[8][2] = 5;
        cross[3][7] = cross[7][3] = 5;
        cross[3][9] = cross[9][3] = 6;
        cross[4][6] = cross[6][4] = 5;
        cross[7][9] = cross[9][7] = 8;

        function<int(int, int)> dfs = [&](int i, int cnt) {
            if (cnt > n) {
                return 0;
            }
            vis[i] = true;
            int ans = cnt >= m ? 1 : 0;
            for (int j = 1; j < 10; ++j) {
                int x = cross[i][j];
                if (!vis[j] && (x == 0 || vis[x])) {
                    ans += dfs(j, cnt + 1);
                }
            }
            vis[i] = false;
            return ans;
        };

        return dfs(1, 1) * 4 + dfs(2, 1) * 4 + dfs(5, 1);
    }
};