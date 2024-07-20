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
    public int removeStones(int[][] stones) {
        int m = 10001;
        UnionFind uf = new UnionFind(m << 1);
        for (var st : stones) {
            uf.union(st[0], st[1] + m);
        }
        Set<Integer> s = new HashSet<>();
        for (var st : stones) {
            s.add(uf.find(st[0]));
        }
        return stones.length - s.size();
    }
}