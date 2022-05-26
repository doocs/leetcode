class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;
        for (int[] e : edges) {
            if (e[0] == 3) {
                if (ufa.union(e[1], e[2])) {
                    ufb.union(e[1], e[2]);
                } else {
                    ++ans;
                }
            }
        }
        for (int[] e : edges) {
            if ((e[0] == 1 && !ufa.union(e[1], e[2])) || (e[0] == 2 && !ufb.union(e[1], e[2]))) {
                ++ans;
            }
        }
        return ufa.n == 1 && ufb.n == 1 ? ans : -1;
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
        int pa = find(a - 1);
        int pb = find(b - 1);
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