class Solution {
public:
    int numberOfCleanRooms(vector<vector<int>>& room) {
        int dirs[5] = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        int m = room.size(), n = room[0].size();
        bool vis[m][n][4];
        memset(vis, false, sizeof(vis));
        int ans = 0;
        while (!vis[i][j][k]) {
            vis[i][j][k] = true;
            ans += room[i][j] == 0 ? 1 : 0;
            room[i][j] = -1;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && room[x][y] != 1) {
                i = x;
                j = y;
            } else {
                k = (k + 1) % 4;
            }
        }
        return ans;
    }
};