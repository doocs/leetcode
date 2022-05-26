public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int i = 0;
        foreach(int num in nums)
        {
            if (i < 2 || num != nums[i - 2])
            {
                nums[i++] = num;
            }
        }
        return i;
    }
}