public class Solution {
    public void NextPermutation(int[] nums) {
        var index1 = -1;
        var index2 = 0;
        for (var i = 1; i < nums.Length; ++i)
        {
            if (nums[i - 1] < nums[i])
            {
                index1 = i - 1;
                index2 = i;
            }
            else if (index1 >= 0 && nums[index1] < nums[i])
            {
                index2 = i;
            }
        }

        if (index1 >= 0)
        {
            var temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
        System.Array.Sort(nums, index1 + 1, nums.Length - index1 - 1);
    }
}