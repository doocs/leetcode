class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) g[e[1]].push_back(e[0]);
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            vector<int> t;
            if (g[i].empty()) {
                ans.push_back(t);
                continue;
            }
            queue<int> q {{i}};
            vector<bool> vis(n);
            vis[i] = true;
            while (!q.empty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.front();
                    q.pop();
                    for (int u : g[v]) {
                        if (vis[u]) continue;
                        vis[u] = true;
                        q.push(u);
                        t.push_back(u);
                    }
                }
            }
            sort(t.begin(), t.end());
            ans.push_back(t);
        }
        return ans;
    }
};