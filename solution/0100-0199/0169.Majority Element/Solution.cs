public class Solution {
    public int MajorityElement(int[] nums) {
        return Sort(nums, 0, nums.Length - 1);
    }

    private int Sort(int[] nums, int left, int right)
    {
        if (left == right) return nums[left];
        var targetIndex = nums.Length / 2;
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
        if (targetIndex <= j) return Sort(nums, left, j);
        if (targetIndex >= i) return Sort(nums, i, right);
        return mid;
    }
}