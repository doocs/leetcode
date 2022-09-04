class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][] f = new int[3010][3010];
    private int j;

    public int numberOfWays(int startPos, int endPos, int k) {
        startPos += 1000;
        endPos += 1000;
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        j = endPos;
        return dfs(startPos, k);
    }

    private int dfs(int i, int k) {
        if (Math.abs(i - j) > k) {
            return 0;
        }
        if (f[i][k] != -1) {
            return f[i][k];
        }
        if (k == 0) {
            return i == j ? 1 : 0;
        }
        int res = dfs(i + 1, k - 1) + dfs(i - 1, k - 1);
        res %= MOD;
        f[i][k] = res;
        return res;
    }
}