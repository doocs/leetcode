class Solution {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0;; ++i) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
    }
}