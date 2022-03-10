class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            // if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 == 1 && nums[mid] != nums[mid - 1])) {
            if (nums[mid] != nums[mid ^ 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}