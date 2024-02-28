public class Solution {
    public long MinIncrementOperations(int[] nums, int k) {
        long f = 0, g = 0, h = 0;
        foreach (int x in nums) {
            long hh = Math.Min(Math.Min(f, g), h) + Math.Max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return Math.Min(Math.Min(f, g), h);
    }
}