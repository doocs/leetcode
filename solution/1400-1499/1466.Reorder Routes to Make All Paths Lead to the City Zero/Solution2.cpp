class Solution {
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        vector<pair<int, int>> g[n];
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b, 1);
            g[b].emplace_back(a, 0);
        }
        queue<int> q{{0}};
        vector<bool> vis(n);
        vis[0] = true;
        int ans = 0;
        while (q.size()) {
            int a = q.front();
            q.pop();
            for (auto& [b, c] : g[a]) {
                if (!vis[b]) {
                    vis[b] = true;
                    q.push(b);
                    ans += c;
                }
            }
        }
        return ans;
    }
};
