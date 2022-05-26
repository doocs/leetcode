public class Solution {
    public int MaxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            int m = maxf, n = minf;
            maxf = Math.Max(nums[i], Math.Max(nums[i] * m, nums[i] * n));
            minf = Math.Min(nums[i], Math.Min(nums[i] * m, nums[i] * n));
            res = Math.Max(res, maxf);
        }
        return res;
    }
}