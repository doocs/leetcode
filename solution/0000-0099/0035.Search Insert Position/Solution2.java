class Solution {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return 0;

        int left = 0, right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}