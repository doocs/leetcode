class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        // find first position
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) {
            return new int[]{-1, -1};
        }
        int l = left;

        // find last position
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{l, left};
    }
}