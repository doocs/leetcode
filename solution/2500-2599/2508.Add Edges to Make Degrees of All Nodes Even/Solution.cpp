class Solution {
public:
    bool isPossible(int n, vector<vector<int>>& edges) {
        vector<unordered_set<int>> g(n + 1);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        vector<int> vs;
        for (int i = 1; i <= n; ++i) {
            if (g[i].size() % 2) {
                vs.emplace_back(i);
            }
        }
        if (vs.size() == 0) {
            return true;
        }
        if (vs.size() == 2) {
            int a = vs[0], b = vs[1];
            if (!g[a].count(b)) return true;
            for (int c = 1; c <= n; ++c) {
                if (a != b && b != c && !g[a].count(c) && !g[c].count(b)) {
                    return true;
                }
            }
            return false;
        }
        if (vs.size() == 4) {
            int a = vs[0], b = vs[1], c = vs[2], d = vs[3];
            if (!g[a].count(b) && !g[c].count(d)) return true;
            if (!g[a].count(c) && !g[b].count(d)) return true;
            if (!g[a].count(d) && !g[b].count(c)) return true;
            return false;
        }
        return false;
    }
};