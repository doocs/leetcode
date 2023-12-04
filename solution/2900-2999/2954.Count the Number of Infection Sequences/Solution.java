class Solution {
    private static final int MOD = (int) (1e9 + 7);
    private static final int MX = 100000;
    private static final int[] FAC = new int[MX + 1];

    static {
        FAC[0] = 1;
        for (int i = 1; i <= MX; i++) {
            FAC[i] = (int) ((long) FAC[i - 1] * i % MOD);
        }
    }

    public int numberOfSequence(int n, int[] sick) {
        int m = sick.length;
        int[] nums = new int[m + 1];
        nums[0] = sick[0];
        nums[m] = n - sick[m - 1] - 1;
        for (int i = 1; i < m; i++) {
            nums[i] = sick[i] - sick[i - 1] - 1;
        }
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        int ans = FAC[s];
        for (int x : nums) {
            if (x > 0) {
                ans = (int) ((long) ans * qpow(FAC[x], MOD - 2) % MOD);
            }
        }
        for (int i = 1; i < nums.length - 1; ++i) {
            if (nums[i] > 1) {
                ans = (int) ((long) ans * qpow(2, nums[i] - 1) % MOD);
            }
        }
        return ans;
    }

    private int qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
        }
        return (int) ans;
    }
}