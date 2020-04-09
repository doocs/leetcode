using System;

public class Solution {
    public int MinSubArrayLen(int s, int[] nums) {
        var result = int.MaxValue;
        var l = 0;
        var r = -1;
        var sum = 0;
        while (r + 1 < nums.Length)
        {
            sum += nums[++r];
            while (sum - nums[l] >= s)
            {
                sum -= nums[l++];
            }
            if (sum >= s)
            {
                result = Math.Min(result, r - l + 1);
                sum -= nums[l++];
            }
        }

        if (result == int.MaxValue) result = 0;
        return result;
    }
}