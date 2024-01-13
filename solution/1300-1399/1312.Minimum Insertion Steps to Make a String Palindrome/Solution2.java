class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
}