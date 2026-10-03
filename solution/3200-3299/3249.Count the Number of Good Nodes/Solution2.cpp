class Solution {
public:
    int countGoodNodes(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0;
        vector<int> sz(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int a = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push_back({a, fa, 1});
                for (int b : g[a]) {
                    if (b != fa) {
                        stk.push_back({b, a, 0});
                    }
                }
            } else {
                int pre = -1, cnt = 1, ok = 1;
                for (int b : g[a]) {
                    if (b != fa) {
                        int curSz = sz[b];
                        cnt += curSz;
                        if (pre < 0) {
                            pre = curSz;
                        } else if (pre != curSz) {
                            ok = 0;
                        }
                    }
                }
                ans += ok;
                sz[a] = cnt;
            }
        }
        return ans;
    }
};
