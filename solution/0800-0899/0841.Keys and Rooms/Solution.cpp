class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        int cnt = 0;
        bool vis[n];
        memset(vis, false, sizeof(vis));
        function<void(int)> dfs = [&](int i) {
            if (vis[i]) {
                return;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                dfs(j);
            }
        };
        dfs(0);
        return cnt == n;
    }
};