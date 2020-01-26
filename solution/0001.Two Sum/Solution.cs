using System.Collections.Generic;

public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var dict = new Dictionary<int, int>();
        for (var i = 0; i < nums.Length; ++i)
        {
            int index;
            if (dict.TryGetValue(target - nums[i], out index))
            {
                return new [] { index + 1, i + 1};
            }
            if (!dict.ContainsKey(nums[i]))
            {
                dict.Add(nums[i], i);
            }
        }
        return null;
    }
}