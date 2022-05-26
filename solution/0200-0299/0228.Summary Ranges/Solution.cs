using System.Collections.Generic;

public class Solution {
    public IList<string> SummaryRanges(int[] nums) {
        var result = new List<string>();
        int? start = null;
        for (var i = 0; i < nums.Length; ++i)
        {
            if (start == null) start = nums[i];
            if (i == nums.Length - 1 || nums[i] + 1 < nums[i + 1])
            {
                result.Add(start == nums[i] ? start.ToString() : string.Format("{0}->{1}", start, nums[i]));
                start = null;
            }
        }
        return result;
    }
}