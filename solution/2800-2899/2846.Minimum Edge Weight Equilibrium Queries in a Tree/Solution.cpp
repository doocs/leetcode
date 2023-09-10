class Solution {
public:
    vector<int> minOperationsQueries(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int m = 32 - __builtin_clz(n);
        vector<pair<int, int>> g[n];
        int f[n][m];
        int p[n];
        int cnt[n][26];
        int depth[n];
        memset(f, 0, sizeof(f));
        memset(cnt, 0, sizeof(cnt));
        memset(depth, 0, sizeof(depth));
        memset(p, 0, sizeof(p));
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2] - 1;
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        queue<int> q;
        q.push(0);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (auto& [j, w] : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    memcpy(cnt[j], cnt[i], sizeof(cnt[i]));
                    cnt[j][w]++;
                    depth[j] = depth[i] + 1;
                    q.push(j);
                }
            }
        }
        vector<int> ans;
        for (auto& qq : queries) {
            int u = qq[0], v = qq[1];
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
            int mx = 0;
            for (int j = 0; j < 26; ++j) {
                mx = max(mx, cnt[u][j] + cnt[v][j] - 2 * cnt[x][j]);
            }
            ans.push_back(depth[u] + depth[v] - 2 * depth[x] - mx);
        }
        return ans;
    }
};