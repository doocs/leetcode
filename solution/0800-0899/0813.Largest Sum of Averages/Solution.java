class Solution {
    private double[][] f;
    private int[] s;
    private int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        s = new int[n + 1];
        f = new double[n + 1][k + 1];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        return dfs(0, k);
    }

    private double dfs(int i, int k) {
        if (i == n) {
            return 0;
        }
        if (k == 1) {
            return (s[n] - s[i]) * 1.0 / (n - i);
        }
        if (f[i][k] >= 0) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = i; j < n; ++j) {
            double t = (s[j + 1] - s[i]) * 1.0 / (j - i + 1) + dfs(j + 1, k - 1);
            ans = Math.max(ans, t);
        }
        f[i][k] = ans;
        return ans;
    }
}