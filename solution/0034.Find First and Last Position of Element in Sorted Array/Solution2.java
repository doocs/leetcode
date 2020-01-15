class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                int resL = mid, resR = mid;
                while (resL > left && nums[resL - 1] == target) resL--;
                while (resR < right && nums[resR + 1] == target) resR++;
                return new int[]{resL, resR};
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }
}