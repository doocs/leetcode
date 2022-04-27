class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        for (int i = 0; i < edges.length; ++i) {
            int[] e = edges[i];
            int[] t = new int[4];
            System.arraycopy(e, 0, t, 0, 3);
            t[3] = i;
            edges[i] = t;
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int v = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            int f = e[0], t = e[1], w = e[2];
            if (uf.union(f, t)) {
                v += w;
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            ans.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int f = e[0], t = e[1], w = e[2], i = e[3];
            uf = new UnionFind(n);
            int k = 0;
            for (int[] ne : edges) {
                int x = ne[0], y = ne[1], z = ne[2], j = ne[3];
                if (j != i && uf.union(x, y)) {
                    k += z;
                }
            }
            if (uf.getN() > 1 || (uf.getN() == 1 && k > v)) {
                ans.get(0).add(i);
                continue;
            }
            uf = new UnionFind(n);
            uf.union(f, t);
            k = w;
            for (int[] ne : edges) {
                int x = ne[0], y = ne[1], z = ne[2], j = ne[3];
                if (j != i && uf.union(x, y)) {
                    k += z;
                }
            }
            if (k == v) {
                ans.get(1).add(i);
            }
        }
        return ans;
    }
}

class UnionFind {
    private int[] p;
    private int n;

    public UnionFind(int n) {
        p = new int[n];
        this.n = n;
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
    }

    public int getN() {
        return n;
    }

    public boolean union(int a, int b) {
        if (find(a) == find(b)) {
            return false;
        }
        p[find(a)] = find(b);
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