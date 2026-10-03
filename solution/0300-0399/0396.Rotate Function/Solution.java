class Solution {
    public int maxRotateFunction(int[] nums) {
        long f = 0;
        long s = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            f += 1L * i * nums[i];
            s += nums[i];
        }
        long ans = f;
        for (int i = 1; i < n; ++i) {
            f = f + s - 1L * n * nums[n - i];
            ans = Math.max(ans, f);
        }
        return (int) ans;
    }
}