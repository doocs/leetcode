class Solution {
public:
    const int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dist(n, inf);
        vector<vector<vector<int>>> g(n);
        for (auto& e : times) {
            int u = e[0] - 1, v = e[1] - 1, w = e[2];
            g[u].push_back({v, w});
        }
        vector<bool> vis(n);
        --k;
        queue<int> q{{k}};
        vis[k] = true;
        dist[k] = 0;
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            vis[u] = false;
            for (auto& ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    if (!vis[v]) {
                        q.push(v);
                        vis[v] = true;
                    }
                }
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};