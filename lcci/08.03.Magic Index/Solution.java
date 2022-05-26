class Solution {
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        return find(nums, left, right);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
}