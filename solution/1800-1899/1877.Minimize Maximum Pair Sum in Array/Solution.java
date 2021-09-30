class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for (int i = 0; i < (n >> 1); ++i) {
            res = Math.max(res, nums[i] + nums[n - i - 1]);
        }
        return res;
    }
}