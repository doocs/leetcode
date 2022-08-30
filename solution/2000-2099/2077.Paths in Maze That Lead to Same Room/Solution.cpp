class Solution {
public:
    int numberOfPaths(int n, vector<vector<int>>& corridors) {
        vector<unordered_set<int>> g(n + 1);
        for (auto& c : corridors) {
            int a = c[0], b = c[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        int ans = 0;
        for (int c = 1; c <= n; ++c) {
            vector<int> nxt;
            nxt.assign(g[c].begin(), g[c].end());
            int m = nxt.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = nxt[i], b = nxt[j];
                    ans += g[b].count(a);
                }
            }
        }
        return ans / 3;
    }
};