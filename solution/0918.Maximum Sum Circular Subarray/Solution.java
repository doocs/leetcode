class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int tot = 0;
        int curMax = 0;
        int maxSum = Integer.MIN_VALUE;
        int curMin = 0;
        int minSum = Integer.MAX_VALUE;
        for (int x : A) {
            tot += x;
            curMax = Math.max(curMax + x, x);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + x, x);
            minSum = Math.min(minSum, curMin);
        }
        return maxSum > 0 ? Math.max(maxSum, tot - minSum) : maxSum;
    }
}
