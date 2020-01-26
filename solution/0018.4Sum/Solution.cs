using System;
using System.Collections.Generic;
using System.Linq;

class FourSumComparer : IEqualityComparer<IList<int>>
{
    public bool Equals(IList<int> left, IList<int> right)
    {
        return left[0] == right[0] && left[1] == right[1] && left[2] == right[2] && left[3] == right[3];
    }

    public int GetHashCode(IList<int> obj)
    {
        return (obj[0] ^ obj[1] ^ obj[2] ^ obj[3]).GetHashCode();
    }
}

public class Solution {
    public IList<IList<int>> FourSum(int[] nums, int target) {
        Array.Sort(nums);
        var results = new HashSet<IList<int>>(new FourSumComparer());
        for (var i = 0; i < nums.Length; ++i)
        {
            for (var j = i + 1; j < nums.Length; ++j)
            {
                var sum = target - nums[i] - nums[j];
                var k = j + 1;
                var l = nums.Length - 1;
                var step = (int)Math.Sqrt(nums.Length);
                while (k < l)
                {
                    while (k + step < l && nums[k + step] + nums[l] < sum)
                    {
                        k += step;
                    }
                    while (k < l && nums[k] + nums[l] < sum)
                    {
                        k += 1;
                    }
                    if (k < l && nums[k] + nums[l] == sum)
                    {
                        results.Add(new [] { nums[i], nums[j], nums[k], nums[l] });
                        ++k;
                    }
                    while (k + step < l && nums[k] + nums[l - step] > sum)
                    {
                        l -= step;
                    }
                    while (k < l && nums[k] + nums[l] > sum)
                    {
                        l -= 1;
                    }
                    if (k < l && nums[k] + nums[l] == sum)
                    {
                        results.Add(new [] { nums[i], nums[j], nums[k], nums[l] });
                        --l;
                    }
                }
            }
        }

        return results.ToList();
    }
}