class Solution {
public:
    vector<int> lastMarkedNodes(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        g.resize(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        auto max_node = [&](const vector<int>& dist) {
            int mx = ranges::max(dist);
            return ranges::find(dist, mx) - dist.begin();
        };
        vector<int> dist1(n);
        dfs(0, -1, dist1);
        int a = max_node(dist1);

        vector<int> dist2(n);
        dfs(a, -1, dist2);
        int b = max_node(dist2);

        vector<int> dist3(n);
        dfs(b, -1, dist3);

        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(dist2[i] > dist3[i] ? a : b);
        }
        return ans;
    }

private:
    vector<vector<int>> g;

    void dfs(int i, int fa, vector<int>& dist) {
        for (int j : g[i]) {
            if (j != fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
            }
        }
    }
};
