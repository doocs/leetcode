public class ThreeSumComparer: IEqualityComparer<IList<int>>
{
    public bool Equals(IList<int> left, IList<int> right)
    {
        return left[0] == right[0] && left[1] == right[1] && left[2] == right[2];
    }
    
    public int GetHashCode(IList<int> obj)
    {
        return (obj[0] ^ obj[1] ^ obj[2]).GetHashCode();
    }
}

public class Solution {
    public IList<IList<int>> ThreeSum(int[] nums) {
        Array.Sort(nums);
        var results = new HashSet<IList<int>>(new ThreeSumComparer());
        
        var cIndex = Array.BinarySearch(nums, 0);
        if (cIndex < 0) cIndex = ~cIndex;
        while (cIndex < nums.Length)
        {
            var c = nums[cIndex];
            var aIndex = 0;
            var bIndex = cIndex - 1;
            while (aIndex < bIndex)
            {
                if (nums[aIndex] + nums[bIndex] + c < 0)
                {
                    var step = 1;
                    while (aIndex + step < bIndex && nums[aIndex + step] + nums[bIndex] + c < 0)
                    {
                        aIndex += step;
                        step *= 2;
                    }
                    step /= 2;
                    while (step > 0)
                    {
                        if (aIndex + step < bIndex && nums[aIndex + step] + nums[bIndex] + c < 0)
                        {
                            aIndex += step;
                        }
                        step /= 2;
                    }
                }
                
                if (nums[aIndex] + nums[bIndex] + c > 0)
                {
                    var step = 1;
                    while (aIndex < bIndex - step && nums[aIndex] + nums[bIndex - step] + c > 0)
                    {
                        bIndex -= step;
                        step *= 2;
                    }
                    step /= 2;
                    while (step > 0)
                    {
                        if (aIndex < bIndex - step && nums[aIndex] + nums[bIndex - step] + c > 0)
                        {
                            bIndex -= step;
                        }
                        step /= 2;
                    }
                }
                
                if (nums[aIndex] + nums[bIndex] + c == 0)
                {
                    var list = new List<int> { nums[aIndex], nums[bIndex], c };
                    results.Add(list);
                    ++aIndex;
                    --bIndex;
                }
                else if (nums[aIndex] + nums[bIndex] + c < 0)
                {
                    ++aIndex;
                }
                else
                {
                    --bIndex;
                }
            }
            ++cIndex;
        }
        
        return results.ToList();
    }
}