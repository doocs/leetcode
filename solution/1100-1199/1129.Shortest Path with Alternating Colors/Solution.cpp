class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        vector<vector<vector<int>>> g(2, vector<vector<int>>(n));
        for (auto& e : redEdges) {
            g[0][e[0]].push_back(e[1]);
        }
        for (auto& e : blueEdges) {
            g[1][e[0]].push_back(e[1]);
        }
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        q.emplace(0, 1);
        bool vis[n][2];
        memset(vis, false, sizeof vis);
        vector<int> ans(n, -1);
        int d = 0;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                auto [i, c] = q.front();
                q.pop();
                if (ans[i] == -1) {
                    ans[i] = d;
                }
                vis[i][c] = true;
                c ^= 1;
                for (int& j : g[c][i]) {
                    if (!vis[j][c]) {
                        q.emplace(j, c);
                    }
                }
            }
            ++d;
        }
        return ans;
    }
};