class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean incr = true, decr = true;
        for (int i = 1; i < nums.length; ++i) {
            if (!incr && !decr) {
                return false;
            }
            if (nums[i] < nums[i - 1]) {
                incr = false;
            } else if (nums[i] > nums[i - 1]) {
                decr = false;
            }
        }
        return incr || decr;
    }
}