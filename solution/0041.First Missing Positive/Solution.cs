public class Solution {
    public int FirstMissingPositive(int[] nums) {
        var i = 0;
        while (i < nums.Length)
        {
            if (nums[i] > 0 && nums[i] <= nums.Length)
            {
                var index = nums[i] -1;
                if (index != i && nums[index] != nums[i])
                {
                    var temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
                else
                {
                    ++i;
                }
            }
            else
            {
                ++i;
            }
        }

        for (i = 0; i < nums.Length; ++i)
        {
            if (nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        return nums.Length + 1;
    }
}