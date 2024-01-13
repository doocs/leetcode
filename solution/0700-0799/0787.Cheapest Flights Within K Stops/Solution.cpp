class Solution {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        const int inf = 0x3f3f3f3f;
        vector<int> dist(n, inf);
        vector<int> backup;
        dist[src] = 0;
        for (int i = 0; i < k + 1; ++i) {
            backup = dist;
            for (auto& e : flights) {
                int f = e[0], t = e[1], p = e[2];
                dist[t] = min(dist[t], backup[f] + p);
            }
        }
        return dist[dst] == inf ? -1 : dist[dst];
    }
};