class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = i;
        }
        for (int[] edge : edges) {
            int p = find(edge[0], f);
            int q = find(edge[1], f);
            if (p == q) {
                return edge;
            }
            f[p] = q;
        }
        return null;
    }

    private int find(int x, int[] f) {
        if (f[x] != x) {
            f[x] = find(f[x], f);
        }
        return f[x];
    }
}
