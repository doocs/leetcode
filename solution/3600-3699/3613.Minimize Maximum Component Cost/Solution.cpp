class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges, int k) {
        if (k == n) {
            return 0;
        }
        vector<int> p(n);
        ranges::iota(p, 0);
        ranges::sort(edges, {}, [](const auto& e) { return e[2]; });
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int cnt = n;
        for (const auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int pu = find(u), pv = find(v);
            if (pu != pv) {
                p[pu] = pv;
                if (--cnt <= k) {
                    return w;
                }
            }
        }
        return 0;
    }
};