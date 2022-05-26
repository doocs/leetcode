class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i - k < 0 || i + k >= n) {
                ans[i] = -1;
            } else {
                ans[i] = (int) ((presum[i + k + 1] - presum[i - k]) / (k * 2 + 1));
            }
        }
        return ans;
    }
}