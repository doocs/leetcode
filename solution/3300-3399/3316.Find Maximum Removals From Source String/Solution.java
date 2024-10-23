class Solution {
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int m = source.length(), n = pattern.length();
        int[][] f = new int[m + 1][n + 1];
        final int inf = Integer.MAX_VALUE / 2;
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        int[] s = new int[m];
        for (int i : targetIndices) {
            s[i] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j] + s[i - 1];
                if (j > 0 && source.charAt(i - 1) == pattern.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1]);
                }
            }
        }
        return f[m][n];
    }
}
