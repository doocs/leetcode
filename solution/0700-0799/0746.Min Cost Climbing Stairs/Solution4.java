class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 2];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = cost[i] + Math.min(f[i + 1], f[i + 2]);
        }
        return Math.min(f[0], f[1]);
    }
}
