class Solution {
    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1;i <= A.length;i++) {
            for (int j = 1;j <= B.length;j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }
}