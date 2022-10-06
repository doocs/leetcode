class Solution {
    public int[] largestSubarray(int[] nums, int k) {
        int i = 0, mx = 0;
        for (int j = 0; j < nums.length - k + 1; ++j) {
            if (mx < nums[j]) {
                mx = nums[j];
                i = j;
            }
        }
        int[] ans = new int[k];
        for (int j = 0; j < k; ++j) {
            ans[j] = nums[i + j];
        }
        return ans;
    }
}