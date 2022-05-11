class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] p = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            p[i] = i;
        }
        UnionFind uf = new UnionFind(n + 1);
        int conflict = -1, cycle = -1;
        for (int i = 0; i < n; ++i) {
            int u = edges[i][0], v = edges[i][1];
            if (p[v] != v) {
                conflict = i;
            } else {
                p[v] = u;
                if (!uf.union(u, v)) {
                    cycle = i;
                }
            }
        }
        if (conflict == -1) {
            return edges[cycle];
        }
        int v = edges[conflict][1];
        if (cycle != -1) {
            return new int[]{p[v], v};
        }
        return edges[conflict];
    }
}

class UnionFind {
    public int[] p;
    public int n;

    public UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        this.n = n;
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        --n;
        return true;
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}