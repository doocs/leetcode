class Solution {
public:
    int shortestPathLength(vector<vector<int>>& graph) {
        int n = graph.size();
        queue<pair<int, int>> q;
        bool vis[n][1 << n];
        memset(vis, false, sizeof(vis));
        for (int i = 0; i < n; ++i) {
            q.emplace(i, 1 << i);
            vis[i][1 << i] = true;
        }
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k; --k) {
                auto [i, st] = q.front();
                q.pop();
                if (st == (1 << n) - 1) {
                    return ans;
                }
                for (int j : graph[i]) {
                    int nst = st | 1 << j;
                    if (!vis[j][nst]) {
                        vis[j][nst] = true;
                        q.emplace(j, nst);
                    }
                }
            }
        }
    }
};