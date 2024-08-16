class Solution {
    public int sumOfPower(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        f[0] = 1;
        for (int x : nums) {
            for (int j = k; j >= 0; --j) {
                f[j] = (f[j] * 2 % mod + (j >= x ? f[j - x] : 0)) % mod;
            }
        }
        return f[k];
    }
}
