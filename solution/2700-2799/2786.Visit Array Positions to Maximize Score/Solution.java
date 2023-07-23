class Solution {
    public long maxScore(int[] nums, int x) {
        long[] f = new long[2];
        Arrays.fill(f, -(1L << 60));
        f[nums[0] & 1] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            f[nums[i] & 1] = Math.max(f[nums[i] & 1] + nums[i], f[nums[i] & 1 ^ 1] + nums[i] - x);
        }
        return Math.max(f[0], f[1]);
    }
}