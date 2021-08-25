class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            if (find(e[0]) == find(e[1])) {
                return false;
            }
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}