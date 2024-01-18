public class Solution {
    public int MaxSubArray(int[] nums) {
        int ans = -1000000000;
        int f = 0;
        foreach (int x in nums) {
            f = Math.Max(f, 0) + x;
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
