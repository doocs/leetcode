class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] cnt = new int[n];
            int one = 0;
            long ans = Long.MAX_VALUE;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = Math.min(ans, (long) k + j - i + 1 - one + f[j + 1]);
            }
            f[i] = (int) ans;
        }
        return f[0];
    }
}
