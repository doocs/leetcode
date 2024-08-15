class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        for (int v : nums) {
            long tmp = f0;
            f0 = Math.max(f0 + v, f1 + (v ^ k));
            f1 = Math.max(f1 + v, tmp + (v ^ k));
        }
        return f0;
    }
}
