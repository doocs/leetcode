class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(nums, (a, b) -> {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        });
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i][0];
            for (int j = 0; j < i; ++j) {
                if (nums[j][0] <= nums[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i][0]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}