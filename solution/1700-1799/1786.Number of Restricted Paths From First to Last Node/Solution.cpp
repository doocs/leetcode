using pii = pair<int, int>;

class Solution {
public:
    const int inf = INT_MAX;
    const int mod = 1e9 + 7;
    vector<vector<pii>> g;
    vector<int> dist;
    vector<int> f;
    int n;

    int countRestrictedPaths(int n, vector<vector<int>>& edges) {
        this->n = n;
        g.resize(n + 1);
        dist.assign(n + 1, inf);
        f.assign(n + 1, -1);
        dist[n] = 0;
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        priority_queue<pii, vector<pii>, greater<pii>> q;
        q.emplace(0, n);
        while (!q.empty()) {
            auto [_, u] = q.top();
            q.pop();
            for (auto [v, w] : g[u]) {
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.emplace(dist[v], v);
                }
            }
        }
        return dfs(1);
    }

    int dfs(int i) {
        if (f[i] != -1) return f[i];
        if (i == n) return 1;
        int ans = 0;
        for (auto [j, _] : g[i]) {
            if (dist[i] > dist[j]) {
                ans = (ans + dfs(j)) % mod;
            }
        }
        f[i] = ans;
        return ans;
    }
};