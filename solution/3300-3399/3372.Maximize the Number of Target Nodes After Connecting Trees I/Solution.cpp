class Solution {
public:
    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2, int k) {
        auto g2 = build(edges2);
        int m = edges2.size() + 1;
        int t = 0;
        for (int i = 0; i < m; ++i) {
            t = max(t, dfs(g2, i, -1, k - 1));
        }

        auto g1 = build(edges1);
        int n = edges1.size() + 1;

        vector<int> ans(n, t);
        for (int i = 0; i < n; ++i) {
            ans[i] += dfs(g1, i, -1, k);
        }

        return ans;
    }

private:
    vector<vector<int>> build(const vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        return g;
    }

    int dfs(const vector<vector<int>>& g, int a, int fa, int d) {
        if (d < 0) {
            return 0;
        }
        int cnt = 1;
        for (int b : g[a]) {
            if (b != fa) {
                cnt += dfs(g, b, a, d - 1);
            }
        }
        return cnt;
    }
};
