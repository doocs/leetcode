class Solution {
    public long minimumCost(int[] nums, int[] cost, int k) {
        int n = nums.length;
        long[] prefixNums = new long[n];
        long[] prefixCosts = new long[n];
        prefixNums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixNums[i] = prefixNums[i - 1] + nums[i];
        }

        prefixCosts[0] = cost[0];
        for (int i = 1; i < n; i++) {
            prefixCosts[i] = prefixCosts[i - 1] + cost[i];
        }

        Long[][] dp = new Long[n][n];
        long ans = solve(0, 0, k, prefixNums, prefixCosts, dp);
        return ans;
    }

    public long solve(int start, int end, int k, long[] prefixNums, long[] prefixCosts, Long[][] dp) {
        int n = prefixNums.length;
        if (end == n) {
            if (start == n) return 0;
            return Long.MAX_VALUE;
        }
        
        if (dp[start][end] != null) return dp[start][end];

        long currentNumsSum = prefixNums[end], currentCostSum = prefixCosts[n - 1];

        if (start != 0){
            currentNumsSum = prefixNums[end] - prefixNums[start - 1];
            currentCostSum = prefixCosts[n - 1] - prefixCosts[start - 1];
        }

        long currentSubarrayCost = (currentNumsSum + k) * currentCostSum;

        long costIfCutHere = currentSubarrayCost + solve(end + 1, end + 1, k, prefixNums, prefixCosts, dp);
        long costIfExtend = solve(start, end + 1, k, prefixNums, prefixCosts, dp);

        return dp[start][end] = Math.min(costIfCutHere, costIfExtend);
    }
}
