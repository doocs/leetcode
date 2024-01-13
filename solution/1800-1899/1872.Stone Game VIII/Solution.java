class Solution {
    private Integer[] f;
    private int[] s;
    private int n;

    public int stoneGameVIII(int[] stones) {
        n = stones.length;
        f = new Integer[n];
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        s = stones;
        return dfs(1);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return s[i];
        }
        if (f[i] == null) {
            f[i] = Math.max(dfs(i + 1), s[i] - dfs(i + 1));
        }
        return f[i];
    }
}