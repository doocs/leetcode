public class Solution {
    public int LongestSubarray(int[] nums) {
        int mx = nums.Max();
        int ans = 0, cnt = 0;
        foreach (int x in nums) {
            if (x == mx) {
                ans = Math.Max(ans, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}
