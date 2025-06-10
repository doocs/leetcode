class Solution {
    public int countPartitions(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        TreeMap<Integer, Integer> sl = new TreeMap<>();
        int n = nums.length;
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        f[0] = 1;
        g[0] = 1;
        int l = 1;
        for (int r = 1; r <= n; r++) {
            int x = nums[r - 1];
            sl.merge(x, 1, Integer::sum);
            while (sl.lastKey() - sl.firstKey() > k) {
                if (sl.merge(nums[l - 1], -1, Integer::sum) == 0) {
                    sl.remove(nums[l - 1]);
                }
                ++l;
            }
            f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
            g[r] = (g[r - 1] + f[r]) % mod;
        }
        return f[n];
    }
}
