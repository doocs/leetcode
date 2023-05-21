using ll = long long;
const int inf = 2e9;

class Solution {
public:
    vector<vector<int>> modifiedGraphEdges(int n, vector<vector<int>>& edges, int source, int destination, int target) {
        ll d = dijkstra(edges, n, source, destination);
        if (d < target) {
            return {};
        }
        bool ok = d == target;
        for (auto& e : edges) {
            if (e[2] > 0) {
                continue;
            }
            if (ok) {
                e[2] = inf;
                continue;
            }
            e[2] = 1;
            d = dijkstra(edges, n, source, destination);
            if (d <= target) {
                ok = true;
                e[2] += target - d;
            }
        }
        return ok ? edges : vector<vector<int>>{};
    }

    ll dijkstra(vector<vector<int>>& edges, int n, int src, int dest) {
        ll g[n][n];
        ll dist[n];
        bool vis[n];
        for (int i = 0; i < n; ++i) {
            fill(g[i], g[i] + n, inf);
            dist[i] = inf;
            vis[i] = false;
        }
        dist[src] = 0;
        for (auto& e : edges) {
            int a = e[0], b = e[1], w = e[2];
            if (w == -1) {
                continue;
            }
            g[a][b] = w;
            g[b][a] = w;
        }
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (k == -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist[dest];
    }
};