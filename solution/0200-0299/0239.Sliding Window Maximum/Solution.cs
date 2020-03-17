using System.Collections.Generic;

public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        if (nums.Length == 0) return new int[0];
        var result = new int[nums.Length - k + 1];
        var descOrderNums = new LinkedList<int>();
        for (var i = 0; i < nums.Length; ++i)
        {
            if (i >= k && nums[i - k] == descOrderNums.First.Value)
            {
                descOrderNums.RemoveFirst();
            }
            while (descOrderNums.Count > 0 && nums[i] > descOrderNums.Last.Value)
            {
                descOrderNums.RemoveLast();
            }
            descOrderNums.AddLast(nums[i]);
            if (i >= k - 1)
            {
                result[i - k + 1] = descOrderNums.First.Value;
            }
        }
        return result;
    }
}