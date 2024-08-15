public class Solution {
    public long MaximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        foreach (int x in nums) {
            long tmp = f0;
            f0 = Math.Max(f0 + x, f1 + (x ^ k));
            f1 = Math.Max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}
