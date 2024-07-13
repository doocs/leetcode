class Solution {
    public long maxScore(int[] nums, int x) {
        long[] f = new long[2];
        Arrays.fill(f, -(1L << 60));
        f[nums[0] & 1] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int v = nums[i];
            f[v & 1] = Math.max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return Math.max(f[0], f[1]);
    }
}