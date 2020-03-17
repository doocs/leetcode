class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int count = 0, length = nums.length - 1;
        for (int i = 0; i <= length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        while (count-- > 0) {
            nums[length - count] = 0;
        }
    }
}