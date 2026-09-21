class Solution {
public:
    vector<int> findMedian(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int m = 32 - __builtin_clz(n);
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        vector<vector<int>> f(n, vector<int>(m));
        vector<int> p(n), depth(n);
        vector<long long> dist(n);
        queue<int> q;
        q.push(0);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (auto [j, w] : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.push(j);
                }
            }
        }
        vector<int> ans;
        for (auto& qq : queries) {
            int u = qq[0], v = qq[1];
            if (u == v) {
                ans.push_back(u);
                continue;
            }
            int x = u, y = v;
            if (depth[x] < depth[y]) {
                swap(x, y);
            }
            for (int j = m - 1; ~j; --j) {
                if (depth[x] - depth[y] >= (1 << j)) {
                    x = f[x][j];
                }
            }
            for (int j = m - 1; ~j; --j) {
                if (f[x][j] != f[y][j]) {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if (x != y) {
                x = p[x];
            }
            long long w = dist[u] + dist[v] - 2 * dist[x];
            if (2 * (dist[u] - dist[x]) >= w) {
                int cur = u;
                for (int j = m - 1; ~j; --j) {
                    int k = f[cur][j];
                    if (depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w) {
                        cur = k;
                    }
                }
                ans.push_back(p[cur]);
            } else {
                int cur = v;
                for (int j = m - 1; ~j; --j) {
                    int k = f[cur][j];
                    if (depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w) {
                        cur = k;
                    }
                }
                ans.push_back(cur);
            }
        }
        return ans;
    }
};
