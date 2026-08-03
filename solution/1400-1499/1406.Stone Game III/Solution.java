class Solution {
    private int[] stoneValue;
    private Integer[] f;
    private int n;

    public String stoneGameIII(int[] stoneValue) {
        this.stoneValue = stoneValue;
        this.n = stoneValue.length;
        this.f = new Integer[n];

        int res = dfs(0);

        if (res == 0) {
            return "Tie";
        }
        return res > 0 ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }

        if (f[i] != null) {
            return f[i];
        }

        int ans = Integer.MIN_VALUE;
        int s = 0;

        for (int j = i; j < i + 3 && j < n; j++) {
            s += stoneValue[j];
            ans = Math.max(ans, s - dfs(j + 1));
        }

        return f[i] = ans;
    }
}