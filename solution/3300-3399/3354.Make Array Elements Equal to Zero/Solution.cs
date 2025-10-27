public class Solution {
    public int CountValidSelections(int[] nums) {
        int s = nums.Sum();
        int ans = 0, l = 0;
        foreach (int x in nums) {
            if (x != 0) {
                l += x;
            } else if (l * 2 == s) {
                ans += 2;
            } else if (Math.Abs(l * 2 - s) <= 1) {
                ans += 1;
            }
        }
        return ans;
    }
}
