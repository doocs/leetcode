class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        int[] ans = new int[n];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != 0) {
                ans[j++] = nums[i];
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                ans[j++] = nums[i];
            }
        }
        return ans;
    }
}