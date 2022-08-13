class Solution {
public:
    vector<vector<int>> isConnected;
    vector<bool> vis;
    int n;

    int findCircleNum(vector<vector<int>>& isConnected) {
        n = isConnected.size();
        vis.resize(n);
        this->isConnected = isConnected;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }

    void dfs(int i) {
        vis[i] = true;
        for (int j = 0; j < n; ++j)
            if (!vis[j] && isConnected[i][j])
                dfs(j);
    }
};