class Solution {
    public int minimumCost(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        long cnt = 0;
        long cur = k;

        for (int x : nums) {
            long diff = (long) x - cur;
            if (diff > 0) {
                long m = (diff + k - 1L) / k;
                cur += m * (long) k;
                cnt += m;
            }
            cur -= x;
        }

        cnt %= MOD;
        return (int) ((cnt + 1) * cnt / 2 % MOD);
    }
}