class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n + 1);
        for (auto& e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w});
            g[b].push_back({a, w});
        }
        vector<char> vis(n + 1);
        int ans = INT_MAX;
        vector<int> stk{1};
        while (!stk.empty()) {
            int a = stk.back();
            stk.pop_back();
            if (vis[a]) {
                continue;
            }
            vis[a] = 1;
            for (auto& [b, w] : g[a]) {
                ans = min(ans, w);
                if (!vis[b]) {
                    stk.push_back(b);
                }
            }
        }
        return ans;
    }
};
