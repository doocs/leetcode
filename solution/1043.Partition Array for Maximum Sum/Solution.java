class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = 0;
            for (int k = 0; k < K && i - k >= 0; k++) {
                max = Math.max(max, A[i - k]);
                dp[i] = Math.max(dp[i], (i - 1 >= k ? dp[i - k - 1] : 0) + max * (k + 1));
            }
        }
        return dp[A.length - 1];
    }
}
