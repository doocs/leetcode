class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int t = nums[i] + nums[j];
            if (t < k) {
                ans = Math.max(ans, t);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}