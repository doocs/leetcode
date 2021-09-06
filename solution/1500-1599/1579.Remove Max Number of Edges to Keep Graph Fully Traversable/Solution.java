class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int res = 0;
        for (int[] e : edges) {
            if (e[0] == 3) {
                if (!ufa.union(e[1], e[2])) {
                    ++res;
                } else {
                    ufb.union(e[1], e[2]);
                }
            }
        }
        for (int[] e : edges) {
            if (e[0] == 1) {
                if (!ufa.union(e[1], e[2])) {
                    ++res;
                }
            } else if (e[0] == 2) {
                if (!ufb.union(e[1], e[2])) {
                    ++res;
                }
            }
        }
        return ufa.n == 1 && ufb.n == 1 ? res : -1;
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
        int pa = find(a - 1), pb = find(b - 1);
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