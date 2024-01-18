class Solution {
public:
    vector<int> countSubgraphsForEachDiameter(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u].emplace_back(v);
            g[v].emplace_back(u);
        }
        vector<int> ans(n - 1);
        int nxt = 0, msk = 0;
        auto bfs = [&](int u) -> int {
            int d = -1;
            msk ^= 1 << u;
            queue<int> q{{u}};
            while (!q.empty()) {
                ++d;
                for (int k = q.size(); k; --k) {
                    u = q.front();
                    nxt = u;
                    q.pop();
                    for (int& v : g[u]) {
                        if (msk >> v & 1) {
                            msk ^= 1 << v;
                            q.push(v);
                        }
                    }
                }
            }
            return d;
        };
        for (int mask = 1; mask < 1 << n; ++mask) {
            if ((mask & (mask - 1)) == 0) {
                continue;
            }
            msk = mask;
            int cur = 31 - __builtin_clz(msk);
            bfs(cur);
            if (msk == 0) {
                msk = mask;
                int mx = bfs(nxt);
                ++ans[mx - 1];
            }
        }
        return ans;
    }
};