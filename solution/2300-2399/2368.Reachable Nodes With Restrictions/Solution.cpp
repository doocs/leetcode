class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<int> g[n];
        vector<int> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        function<int(int)> dfs = [&](int i) {
            vis[i] = true;
            int ans = 1;
            for (int j : g[i]) {
                if (!vis[j]) {
                    ans += dfs(j);
                }
            }
            return ans;
        };
        return dfs(0);
    }
};