// https://leetcode.com/problems/contains-duplicate-ii/

using System;
using System.Collections.Generic;
using System.Linq;

public partial class Solution
{
    public bool ContainsNearbyDuplicate(int[] nums, int k)
    {
        //var sorted = nums.Select((n, i) => Tuple.Create(n, i)).OrderBy(t => t.Item1).ThenBy(t => t.Item2).ToList();
        //for (var i = 1; i < sorted.Count; ++i)
        //{
        //    if (sorted[i - 1].Item1 == sorted[i].Item1 && sorted[i].Item2 - sorted[i - 1].Item2 <= k)
        //    {
        //        return true;
        //    }
        //}
        //return false;

        if (k <= 0) return false;
        var index = new HashSet<int>();
        for (int i = 0; i < nums.Length; ++i)
        {
            if (index.Contains(nums[i]))
            {
                return true;
            }
            if (index.Count == k)
            {
                index.Remove(nums[i - k]);
            }
            index.Add(nums[i]);
        }
        return false;
    }
}
