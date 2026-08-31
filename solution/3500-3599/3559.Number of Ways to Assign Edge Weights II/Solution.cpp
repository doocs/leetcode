class Solution {
public:
    vector<int> assignEdgeWeights(vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int n = edges.size() + 1;
        int m = 32 - __builtin_clz(n);
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<vector<int>> f(n + 1, vector<int>(m));
        vector<int> p(n + 1), depth(n + 1);
        queue<int> q;
        q.push(1);
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            f[i][0] = p[i];
            for (int j = 1; j < m; ++j) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for (int j : g[i]) {
                if (j != p[i]) {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    q.push(j);
                }
            }
        }
        const int mod = 1e9 + 7;
        vector<int> pow2(n, 1);
        for (int i = 1; i < n; ++i) {
            pow2[i] = pow2[i - 1] * 2 % mod;
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
            int d = depth[u] + depth[v] - 2 * depth[x];
            ans.push_back(d == 0 ? 0 : pow2[d - 1]);
        }
        return ans;
    }
};
