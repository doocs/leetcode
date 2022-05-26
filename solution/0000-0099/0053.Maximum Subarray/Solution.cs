public class Solution {
    public int MaxSubArray(int[] nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            f = nums[i] + Math.Max(f, 0);
            res = Math.Max(res, f);
        }
        return res;
    }
}