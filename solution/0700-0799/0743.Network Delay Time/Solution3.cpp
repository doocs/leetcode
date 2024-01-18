class Solution {
public:
    int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            vector<int> backup = dist;
            for (auto& e : times) {
                int u = e[0] - 1, v = e[1] - 1, w = e[2];
                dist[v] = min(dist[v], backup[u] + w);
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};