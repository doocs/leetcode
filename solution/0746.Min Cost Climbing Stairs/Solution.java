class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int pre1 = 0, pre2 = 0;
        int res = 0;
        for (int i = 2; i <= cost.length; ++i) {
            res = Math.min(pre1 + cost[i - 1], pre2 + cost[i - 2]);
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }
}
