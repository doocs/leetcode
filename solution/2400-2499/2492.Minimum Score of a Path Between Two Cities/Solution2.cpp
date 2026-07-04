class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n + 1);
        for (auto& e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w});
            g[b].push_back({a, w});
        }

        vector<bool> vis(n + 1, false);
        int ans = INT_MAX;
        queue<int> q{{1}};
        vis[1] = true;

        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int a = q.front();
                q.pop();
                for (auto [b, w] : g[a]) {
                    ans = min(ans, w);
                    if (!vis[b]) {
                        vis[b] = true;
                        q.push(b);
                    }
                }
            }
        }
        return ans;
    }
};
