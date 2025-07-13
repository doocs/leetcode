class Solution {
    private int[] p;

    public int minCost(int n, int[][] edges, int k) {
        if (k == n) {
            return 0;
        }
        p = new int[n];
        Arrays.setAll(p, i -> i);
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int cnt = n;
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int pu = find(u), pv = find(v);
            if (pu != pv) {
                p[pu] = pv;
                if (--cnt <= k) {
                    return w;
                }
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}