class Solution {
    public int maxScore(int[] nums) {
        int m = nums.length;
        int[][] f = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                f[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] dp = new int[1 << m];
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = Integer.bitCount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (((k >> i) & 1) == 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (((k >> j) & 1) == 1) {
                                dp[k] = Math.max(dp[k], dp[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * f[i][j]);
                            }
                        }
                    }
                }
            }
        }
        return dp[(1 << m) - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}