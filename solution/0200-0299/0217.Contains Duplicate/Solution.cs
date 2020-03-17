// https://leetcode.com/problems/contains-duplicate/

using System.Linq;

public partial class Solution
{
    public bool ContainsDuplicate(int[] nums)
    {
        return nums.Distinct().Count() < nums.Length;
    }
}
