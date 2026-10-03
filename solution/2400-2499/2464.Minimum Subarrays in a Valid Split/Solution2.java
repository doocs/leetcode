class Solution {
    public int validSubarraySplit(int[] nums) {
        int n = nums.length;
        int inf = 0x3f3f3f3f;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            f[i] = inf;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (gcd(nums[i], nums[j]) > 1) {
                    f[i] = Math.min(f[i], 1 + f[j + 1]);
                }
            }
        }
        return f[0] < inf ? f[0] : -1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
