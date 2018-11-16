class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) start = mid + 1;
            else if (nums[mid] > target) end = mid - 1;
            if (nums[mid] == target) {
                return new int[]{findFirst(nums, start, mid, target),findEnd(nums, mid, end, target)};
            }
        }
        return new int[]{-1,-1};
    }
    private int findFirst(int[] nums, int start, int end, int target) {
        while (start < end) {
            int temp = start + (end - start) / 2;
            if (nums[temp] < target) start = temp + 1;
            else if (nums[temp] == target) end = temp;
        }
        return start;
    }
    private int findEnd(int[] nums, int start, int end, int target) {
        while (start < end) {
            int temp = start + (end - start + 1) / 2;
            if (nums[temp] > target) end = temp - 1;
            else if (nums[temp] == target) start = temp;
        }
        return start;
    }
}