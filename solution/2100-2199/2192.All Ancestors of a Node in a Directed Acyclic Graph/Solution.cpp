class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        vector<vector<int>> ans(n);
        auto bfs = [&](int s) {
            queue<int> q;
            q.push(s);
            bool vis[n];
            memset(vis, 0, sizeof(vis));
            vis[s] = true;
            while (q.size()) {
                int i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        ans[j].push_back(s);
                        q.push(j);
                    }
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            bfs(i);
        }
        return ans;
    }
};