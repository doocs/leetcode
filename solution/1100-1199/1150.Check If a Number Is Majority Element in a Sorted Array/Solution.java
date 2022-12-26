class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int left = search(nums, target);
        int right = search(nums, target + 1);
        return right - left > nums.length / 2;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}