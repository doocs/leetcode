class Solution {
    private int[] p;

    public int[] findRedundantConnection(int[][] edges) {
        p = new int[1010];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) {
                return e;
            }
            p[find(a)] = find(b);
        }
        return null;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}