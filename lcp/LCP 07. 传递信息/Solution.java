class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[] f = new int[n];
        f[0] = 1;
        while (k-- > 0) {
            int[] g = new int[n];
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                g[b] += f[a];
            }
            f = g;
        }
        return f[n - 1];
    }
}