class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) start = mid + 1;
                else end = mid - 1;
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) end = mid - 1;
                else start = mid + 1;
            } else end--;
        }
        return false;
    }
}