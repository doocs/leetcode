class Solution {
    private Boolean[][][] f;
    private String s1;
    private String s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        f = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int k) {
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return f[i][j][k] = true;
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return f[i][j][k] = true;
            }
        }
        return f[i][j][k] = false;
    }
}