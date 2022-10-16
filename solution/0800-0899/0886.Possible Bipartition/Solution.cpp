class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        unordered_map<int, vector<int>> g;
        for (auto& e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        };
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(g[i][0]);
            }
        }
        return true;
    }
};