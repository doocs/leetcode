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
        vector<int> dist1(n);
        dfs(0, -1, dist1);
        int a = max_element(dist1.begin(), dist1.end()) - dist1.begin();

        vector<int> dist2(n);
        dfs(a, -1, dist2);
        int b = max_element(dist2.begin(), dist2.end()) - dist2.begin();

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
