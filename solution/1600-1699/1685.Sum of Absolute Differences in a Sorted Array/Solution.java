class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = nums[i] * i - presum[i] + presum[n] - presum[i + 1] - nums[i] * (n - i - 1);
        }
        return res;
    }
}