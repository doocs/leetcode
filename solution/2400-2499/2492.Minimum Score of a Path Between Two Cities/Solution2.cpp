class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n);
        bool vis[n];
        memset(vis, 0, sizeof vis);
        for (auto& e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].emplace_back(b, d);
            g[b].emplace_back(a, d);
        }
        int ans = INT_MAX;
        queue<int> q{{0}};
        vis[0] = true;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                for (auto [j, d] : g[i]) {
                    ans = min(ans, d);
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }
};