class Solution {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                f[j] = Math.max(f[j], f[i] + (j - i) * nums[j]);
            }
        }
        return f[n - 1];
    }
}