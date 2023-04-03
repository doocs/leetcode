class Solution {
    private int n;
    private int[] s;
    private Integer[] f;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        s = new int[n + 1];
        f = new Integer[n];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : a > b ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int t = 1 << 30;
        for (int j = 1; j < 4; ++j) {
            t = Math.min(t, dfs(i + j));
        }
        f[i] = s[n] - s[i] - t;
        return f[i];
    }
}