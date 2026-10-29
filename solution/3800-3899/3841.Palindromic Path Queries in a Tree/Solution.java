class BinaryIndexedTree {
    int n;
    int[] tree;

    BinaryIndexedTree(int n) {
        this.n = n;
        tree = new int[n + 1];
    }

    void update(int i, int delta) {
        for (i++; i <= n; i += i & -i) {
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
}

class Solution {
    List<Integer>[] g;
    int[] parent, depth, tin, tout, bit;
    int[][] up;
    int timer, m;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;
        dfs(0, -1);

        m = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[m][n];
        System.arraycopy(parent, 0, up[0], 0, n);
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new int[n];
        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s.charAt(i) - 'a');
        }

        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; i++) {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);
            if (parts[0].equals("update")) {
                int x = bit[u];
                int y = 1 << (parts[2].charAt(0) - 'a');
                if (x != y) {
                    int delta = x ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);
                int mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

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
            int t = u;
            u = v;
            v = t;
        }
        int d = depth[u] - depth[v];
        for (int j = 0; d > 0; j++, d >>= 1) {
            if ((d & 1) != 0) {
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
}