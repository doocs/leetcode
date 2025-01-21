class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        int ans = 0;
        bool vis[n];
        memset(vis, false, sizeof(vis));
        auto dfs = [&](this auto&& dfs, int i) -> void {
            vis[i] = true;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && isConnected[i][j]) {
                    dfs(j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }
};
