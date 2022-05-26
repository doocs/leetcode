using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<int> MajorityElement(int[] nums) {
        if (nums.Length == 0) return new int[0];
        var targetIndices = new [] { nums.Length / 3, nums.Length - nums.Length / 3 - 1 }.Distinct().ToList();
        var candidates = Sort(nums, 0, nums.Length - 1, targetIndices).Distinct();
        return candidates.Where(c => nums.Count(n => n == c) > nums.Length / 3).ToList();
    }

    private IList<int> Sort(int[] nums, int left, int right, IList<int> targetIndices)
    {
        if (left == right) return new [] { nums[left] };
        var mid = nums[(left + right) / 2];
        var i = left;
        var j = right;
        while (i <= j)
        {
            while (nums[i] < mid) ++i;
            while (nums[j] > mid) --j;
            if (i <= j)
            {
                var temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                ++i;
                --j;
            }
        }
        var result = new List<int>();
        var leftTargetIndices = new List<int>();
        var rightTargetIndecies = new List<int>();
        foreach (var targetIndex in targetIndices)
        {
            if (targetIndex <= j) leftTargetIndices.Add(targetIndex);
            else if (targetIndex >= i) rightTargetIndecies.Add(targetIndex);
            else result.Add(mid);
        }
        if (leftTargetIndices.Count > 0) result.AddRange(Sort(nums, left, j, leftTargetIndices));
        if (rightTargetIndecies.Count > 0) result.AddRange(Sort(nums, i, right, rightTargetIndecies));
        return result;
    }
}