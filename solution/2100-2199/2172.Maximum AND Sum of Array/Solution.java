class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        int n = nums.length;
        int m = numSlots << 1;
        int[] f = new int[1 << m];
        int ans = 0;
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = Integer.bitCount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}