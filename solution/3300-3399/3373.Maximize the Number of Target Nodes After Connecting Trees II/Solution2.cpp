class Solution {
public:
    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        auto g1 = build(edges1);
        auto g2 = build(edges2);
        int n = g1.size(), m = g2.size();
        vector<int> c1(n), c2(m);
        vector<int> cnt1(2), cnt2(2);
        dfs(g2, c2, cnt2);
        dfs(g1, c1, cnt1);
        int t = max(cnt2[0], cnt2[1]);
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = t + cnt1[c1[i]];
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

    void dfs(const vector<vector<int>>& g, vector<int>& c, vector<int>& cnt) {
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [a, fa, d] = stk.back();
            stk.pop_back();
            c[a] = d;
            cnt[d]++;
            for (int b : g[a]) {
                if (b != fa) {
                    stk.push_back({b, a, d ^ 1});
                }
            }
        }
    }
};
