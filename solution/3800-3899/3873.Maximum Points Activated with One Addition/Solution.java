class UnionFind {
    Map<Long, Long> p = new HashMap<>();
    Map<Long, Integer> size = new HashMap<>();

    long find(long x) {
        if (!p.containsKey(x)) {
            p.put(x, x);
            size.put(x, 1);
        }
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    boolean union(long a, long b) {
        long pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }

        int sa = size.get(pa), sb = size.get(pb);
        if (sa > sb) {
            p.put(pb, pa);
            size.put(pa, sa + sb);
        } else {
            p.put(pa, pb);
            size.put(pb, sa + sb);
        }
        return true;
    }
}

class Solution {
    public int maxActivated(int[][] points) {
        UnionFind uf = new UnionFind();
        long m = (long) 3e9;

        for (int[] p : points) {
            uf.union(p[0], p[1] + m);
        }

        Map<Long, Integer> cnt = new HashMap<>();
        for (int[] p : points) {
            cnt.merge(uf.find(p[0]), 1, Integer::sum);
        }

        int mx1 = 0, mx2 = 0;
        for (int x : cnt.values()) {
            if (mx1 < x) {
                mx2 = mx1;
                mx1 = x;
            } else if (mx2 < x) {
                mx2 = x;
            }
        }

        return mx1 + mx2 + 1;
    }
}
