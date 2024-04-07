class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    public int size(int x) {
        return size[find(x)];
    }
}

class Solution {
    private UnionFind uf;
    private int[] g;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        uf = new UnionFind(n);
        for (var e : edges) {
            uf.union(e[0], e[1]);
        }
        g = new int[n];
        Arrays.fill(g, -1);
        for (var e : edges) {
            int root = uf.find(e[0]);
            g[root] &= e[2];
        }
        int m = query.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int s = query[i][0], t = query[i][1];
            ans[i] = f(s, t);
        }
        return ans;
    }

    private int f(int u, int v) {
        if (u == v) {
            return 0;
        }
        int a = uf.find(u), b = uf.find(v);
        return a == b ? g[a] : -1;
    }
}