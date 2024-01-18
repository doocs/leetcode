class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        final int inf = 1 << 30;
        int[][] f = new int[m + 1][1 << n];
        for (int[] g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        f[i][j] = Math.min(f[i][j], f[i][j ^ (1 << k)] + c);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j] + c);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + c);
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
}