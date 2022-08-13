class Solution {
public:
    vector<int> gardenNoAdj(int n, vector<vector<int>>& paths) {
        vector<vector<int>> g(n);
        for (auto& p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].push_back(y);
            g[y].push_back(x);
        }
        vector<int> ans(n);
        for (int u = 0; u < n; ++u) {
            unordered_set<int> colors;
            for (int v : g[u]) colors.insert(ans[v]);
            for (int c = 1; c < 5; ++c) {
                if (!colors.count(c)) {
                    ans[u] = c;
                    break;
                }
            }
        }
        return ans;
    }
};