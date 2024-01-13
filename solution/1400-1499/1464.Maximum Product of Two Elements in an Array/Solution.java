class Solution {
    public int maxProduct(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                ans = Math.max(ans, (nums[i] - 1) * (nums[j] - 1));
            }
        }
        return ans;
    }
}