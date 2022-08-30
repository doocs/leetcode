public class Solution {
    public int[] Exchange(int[] nums) {
        int i = 0, j = nums.Length - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                ++i;
            }
            while (i < j && nums[j] % 2 == 0) {
                --j;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}