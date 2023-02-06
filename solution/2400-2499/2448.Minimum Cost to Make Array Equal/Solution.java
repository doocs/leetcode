class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums[i], cost[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0], b = arr[i - 1][1];
            f[i] = f[i - 1] + a * b;
            g[i] = g[i - 1] + b;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long a = arr[i - 1][0];
            long l = a * g[i - 1] - f[i - 1];
            long r = f[n] - f[i] - a * (g[n] - g[i]);
            ans = Math.min(ans, l + r);
        }
        return ans;
    }
}