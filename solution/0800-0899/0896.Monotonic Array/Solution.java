class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncr = false, isDecr = false;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                isIncr = true;
            } else if (nums[i] > nums[i - 1]) {
                isDecr = true;
            }
            if (isIncr && isDecr) {
                return false;
            }
        }
        return true;
    }
}