using System;

public class Solution {
    public int MaxProduct(int[] nums) {
        var prevMin = 1;
        var prevMax = 1;
        var result = int.MinValue;
        for (var i = 0; i < nums.Length; ++i)
        {
            var max = Math.Max(nums[i], Math.Max(nums[i] * prevMin, nums[i] * prevMax));
            var min = Math.Min(nums[i], Math.Min(nums[i] * prevMin, nums[i] * prevMax));
            result = Math.Max(result, max);
            prevMax = max;
            prevMin = min;
        }
        return result;
    }
}