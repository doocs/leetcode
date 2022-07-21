public class Solution {
    public int Search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    int helper(int[] nums, int target) {
        int i = 0, j = nums.Length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (nums[mid] <= target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }
}