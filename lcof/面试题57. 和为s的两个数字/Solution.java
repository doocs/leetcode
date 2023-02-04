class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (true) {
            if (nums[l] + nums[r] == target) {
                return new int[] {nums[l], nums[r]};
            }
            if (nums[l] + nums[r] > target) {
                --r;
            } else {
                ++l;
            }
        }
    }
}