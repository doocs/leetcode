class Solution {
    public int search(int[] nums, int target) {
        return solution(nums, target, 0, nums.length - 1);
    }

    private int solution(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // rotation point is to the right && target value is also on the right
            if (nums[mid] > nums[right] && (target < nums[left] || target > nums[mid])) {
                return solution(nums, target, mid + 1, right);
            }
            // rotation point is to the left && target value is also on the left
            if (nums[mid] < nums[left] && (target < nums[mid] || target > nums[right])) {
                return solution(nums, target, left, mid - 1);
            }
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}