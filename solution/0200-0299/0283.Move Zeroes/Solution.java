class Solution {
    public void moveZeroes(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) < 1) {
            return;
        }
        int zeroCount = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                ++zeroCount;
            } else {
                nums[i - zeroCount] = nums[i];
            }
        }
        while (zeroCount > 0) {
            nums[n - zeroCount--] = 0;
        }
    }
}