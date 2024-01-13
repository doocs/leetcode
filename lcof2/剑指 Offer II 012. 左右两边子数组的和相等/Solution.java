class Solution {
    public int pivotIndex(int[] nums) {
        int left = 0, right = 0;
        for (int x : nums) {
            right += x;
        }
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}