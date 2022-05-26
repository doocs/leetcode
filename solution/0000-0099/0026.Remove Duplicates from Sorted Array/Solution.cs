public class Solution {
    public int RemoveDuplicates(int[] nums) {
        if (nums.Length < 2) return nums.Length;
        var i = 0;
        var j = 1;
        while (j < nums.Length)
        {
            if (nums[i] != nums[j])
            {
                nums[++i] = nums[j];
            }
            ++j;
        }
        return i + 1;
    }
}