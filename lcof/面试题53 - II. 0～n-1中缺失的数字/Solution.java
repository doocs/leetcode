class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        if (r == 0 || nums[0] == 1) {
            return nums[0] ^ 1;
        }
        if (nums[r] == r) {
            return r + 1;
        }
        while (r - l > 1) {
            int m = (l + r) >>> 1;
            if (nums[m] == m) {
                l = m;
            } else {
                r = m;
            }
        }
        return nums[r] - 1;
    }
}