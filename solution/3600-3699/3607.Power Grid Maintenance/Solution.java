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
}

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        UnionFind uf = new UnionFind(c + 1);
        for (int[] e : connections) {
            uf.union(e[0], e[1]);
        }

        TreeSet<Integer>[] st = new TreeSet[c + 1];
        Arrays.setAll(st, k -> new TreeSet<>());
        for (int i = 1; i <= c; i++) {
            int root = uf.find(i);
            st[root].add(i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int a = q[0], x = q[1];
            int root = uf.find(x);

            if (a == 1) {
                if (st[root].contains(x)) {
                    ans.add(x);
                } else if (!st[root].isEmpty()) {
                    ans.add(st[root].first());
                } else {
                    ans.add(-1);
                }
            } else {
                st[root].remove(x);
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
