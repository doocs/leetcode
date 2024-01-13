class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0;; ++i) {
            while (nums[i] != i) {
                int j = nums[i];
                if (nums[j] == j) {
                    return j;
                }
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
    }
}