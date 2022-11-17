class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long ans = 0, p = 1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            ans = (ans + (nums[i] - nums[n - i - 1]) * p + MOD) % MOD;
            p = (p << 1) % MOD;
        }
        return (int) ans;
    }
}