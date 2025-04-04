class Solution {

    public long minOperations(int[] nums, int x, int k) {
        TreeSet<Integer> set1 = new TreeSet<>((o, p) -> nums[o] == nums[p] ? o - p : nums[o] - nums[p]), set2 = new TreeSet<>((o, p) -> nums[o] == nums[p] ? o - p : nums[o] - nums[p]);
        long sum[] = new long[nums.length - x + 1], left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            set2.add(i);
            left += nums[set2.first()];
            right += nums[i] - nums[set2.first()];
            set1.add(set2.pollFirst());
            if (set1.size() > set2.size()) {
                left -= nums[set1.last()];
                right += nums[set1.last()];
                set2.add(set1.pollLast());
            }
            if (i >= x - 1) {
                sum[i - x + 1] = nums[set2.first()] * (set1.size() - set2.size()) - left + right;
                left -= set1.remove(i - x + 1) ? nums[i - x + 1] : 0;
                right -= set2.remove(i - x + 1) ? nums[i - x + 1] : 0;
            }
        }
        long[][] dp = new long[sum.length + x][k + 1];
        for (int i = 0; i < sum.length + x; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = 1000000000000000000L;
            }
        }
        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i + x][j] = Math.min(dp[i + x - 1][j], sum[i] + dp[i][j - 1]);
            }
        }
        return dp[sum.length + x - 1][k];
    }
}