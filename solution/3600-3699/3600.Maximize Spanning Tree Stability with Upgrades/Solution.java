class UnionFind {
    int[] p, size;
    int cnt;

    UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        cnt = n;
        for (int i = 0; i < n; i++) {
            p[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        cnt--;
        return true;
    }
}

class Solution {

    int n;
    int[][] edges;
    int k;

    private boolean check(int lim) {
        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s >= lim) {
                uf.union(u, v);
            }
        }

        int rem = k;
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s * 2 >= lim && rem > 0) {
                if (uf.union(u, v)) {
                    rem--;
                }
            }
        }

        return uf.cnt == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {
        this.n = n;
        this.edges = edges;
        this.k = k;

        UnionFind uf = new UnionFind(n);
        int mn = (int) 1e6;

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                mn = Math.min(mn, s);
                if (!uf.union(u, v)) {
                    return -1;
                }
            }
        }

        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }

        if (uf.cnt > 1) {
            return -1;
        }

        int l = 1, r = mn;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
