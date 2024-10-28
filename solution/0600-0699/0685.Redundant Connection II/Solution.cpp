class Solution {
public:
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<int> ind(n);
        for (const auto& e : edges) {
            ++ind[e[1] - 1];
        }
        vector<int> dup;
        for (int i = 0; i < n; ++i) {
            if (ind[edges[i][1] - 1] == 2) {
                dup.push_back(i);
            }
        }
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return x == p[x] ? x : p[x] = find(p[x]);
        };
        if (!dup.empty()) {
            for (int i = 0; i < n; ++i) {
                if (i == dup[1]) {
                    continue;
                }
                int pu = find(edges[i][0] - 1);
                int pv = find(edges[i][1] - 1);
                if (pu == pv) {
                    return edges[dup[0]];
                }
                p[pu] = pv;
            }
            return edges[dup[1]];
        }
        for (int i = 0;; ++i) {
            int pu = find(edges[i][0] - 1);
            int pv = find(edges[i][1] - 1);
            if (pu == pv) {
                return edges[i];
            }
            p[pu] = pv;
        }
    }
};
