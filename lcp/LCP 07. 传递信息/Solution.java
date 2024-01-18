class Solution {
    public int numWays(int n, int[][] relation, int k) {
        int[][] f = new int[k + 1][n];
        f[0][0] = 1;
        for (int i = 1; i <= k; ++i) {
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
}