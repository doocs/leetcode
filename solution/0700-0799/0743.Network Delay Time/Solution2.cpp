class Solution {
public:
    const int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<vector<int>>> g(n);
        for (auto& t : times) g[t[0] - 1].push_back({t[1] - 1, t[2]});
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> q;
        q.push({0, k - 1});
        while (!q.empty()) {
            auto p = q.top();
            q.pop();
            int u = p[1];
            for (auto& ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.push({dist[v], v});
                }
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};