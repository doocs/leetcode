class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int i = 1, n = nums.length;
        for (; i < n && nums[i - 1] < nums[i]; ++i);
        return check(nums, i - 1) || check(nums, i);
    }

    private boolean check(int[] nums, int i) {
        int prev = Integer.MIN_VALUE;
        for (int j = 0; j < nums.length; ++j) {
            if (i == j) {
                continue;
            }
            if (prev >= nums[j]) {
                return false;
            }
            prev = nums[j];
        }
        return true;
    }
}