public class Solution {
    public int TriangularSum(int[] nums) {
        for (int k = nums.Length - 1; k > 0; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
