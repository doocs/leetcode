class Solution {
public:
    int N = 110;
    int INF = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<int>> g(N, vector<int>(N, INF));
        for (auto& e : times) g[e[0]][e[1]] = e[2];
        vector<int> dist(N, INF);
        dist[k] = 0;
        vector<bool> vis(N);
        for (int i = 0; i < n; ++i)
        {
            int t = -1;
            for (int j = 1; j <= n; ++j)
            {
                if (!vis[j] && (t == -1 || dist[t] > dist[j]))
                {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 1; j <= n; ++j)
            {
                dist[j] = min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = *max_element(dist.begin() + 1, dist.begin() + 1 + n);
        return ans == INF ? -1 : ans;
    }
};