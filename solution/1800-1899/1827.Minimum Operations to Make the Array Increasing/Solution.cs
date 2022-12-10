public class Solution {
    public int MinOperations(int[] nums) {
        int ans = 0, mx = 0;
        foreach (int v in nums) {
            ans += Math.Max(0, mx + 1 - v);
            mx = Math.Max(mx + 1, v);
        }
        return ans;
    }
}