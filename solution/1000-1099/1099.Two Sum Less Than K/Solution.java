class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        for (int i = 0, j = nums.length - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = Math.max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}