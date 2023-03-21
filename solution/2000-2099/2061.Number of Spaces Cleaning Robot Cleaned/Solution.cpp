class Solution {
public:
    int numberOfCleanRooms(vector<vector<int>>& room) {
        int m = room.size(), n = room[0].size();
        bool vis[m][n][4];
        memset(vis, false, sizeof(vis));
        int dirs[5] = {0, 1, 0, -1, 0};
        int ans = 0;
        function<void(int, int, int)> dfs = [&](int i, int j, int k) {
            if (vis[i][j][k]) {
                return;
            }
            ans += room[i][j] == 0;
            room[i][j] = -1;
            vis[i][j][k] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1) {
                dfs(x, y, k);
            } else {
                dfs(i, j, (k + 1) % 4);
            }
        };
        dfs(0, 0, 0);
        return ans;
    }
};