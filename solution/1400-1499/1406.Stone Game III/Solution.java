class Solution {
    private int[] stoneValue;
    private Integer[] f;
    private int n;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        f = new Integer[n];
        this.stoneValue = stoneValue;
        int ans = dfs(0);
        if (ans == 0) {
            return "Tie";
        }
        return ans > 0 ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = -(1 << 30);
        int s = 0;
        for (int j = 0; j < 3 && i + j < n; ++j) {
            s += stoneValue[i + j];
            ans = Math.max(ans, s - dfs(i + j + 1));
        }
        return f[i] = ans;
    }
}