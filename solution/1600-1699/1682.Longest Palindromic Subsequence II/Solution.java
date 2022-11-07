class Solution {
    private int[][][] f;
    private String s;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        this.s = s;
        f = new int[n][n][27];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, n - 1, 26);
    }

    private int dfs(int i, int j, int x) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j][x] != -1) {
            return f[i][j][x];
        }
        int ans = 0;
        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != x) {
            ans = dfs(i + 1, j - 1, s.charAt(i) - 'a') + 2;
        } else {
            ans = Math.max(dfs(i + 1, j, x), dfs(i, j - 1, x));
        }
        f[i][j][x] = ans;
        return ans;
    }
}