class BinaryIndexedTree {
    int n;
    vector<int> tree;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , tree(n + 1) {}

    void update(int i, int delta) {
        for (++i; i <= n; i += i & -i) {
            tree[i] ^= delta;
        }
    }

    int query(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= tree[i];
        }
        return res;
    }
};

class Solution {
    vector<vector<int>> g, up;
    vector<int> parent, depth, tin, tout, bit;
    int timer, m;

    void dfs(int u, int p) {
        parent[u] = p == -1 ? 0 : p;
        tin[u] = timer++;
        for (int v : g[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            swap(u, v);
        }
        int d = depth[u] - depth[v];
        for (int j = 0; d; j++, d >>= 1) {
            if (d & 1) {
                u = up[j][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (int j = m - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }

public:
    vector<bool> palindromePath(int n, vector<vector<int>>& edges, string s, vector<string>& queries) {
        g.assign(n, {});
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
            g[e[1]].push_back(e[0]);
        }

        parent.resize(n);
        depth.assign(n, 0);
        tin.resize(n);
        tout.resize(n);
        timer = 0;
        dfs(0, -1);

        m = 32 - __builtin_clz(n);
        up.assign(m, vector<int>(n));
        up[0] = parent;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit.resize(n);
        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s[i] - 'a');
        }

        BinaryIndexedTree tree(n);
        for (int i = 0; i < n; i++) {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        vector<bool> ans;
        for (string& q : queries) {
            stringstream ss(q);
            string op;
            int u;
            ss >> op >> u;
            if (op == "update") {
                string c;
                ss >> c;
                int y = 1 << (c[0] - 'a');
                if (bit[u] != y) {
                    int delta = bit[u] ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                int v;
                ss >> v;
                int w = lca(u, v);
                int mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.push_back((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
};