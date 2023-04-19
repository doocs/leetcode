public class Solution {
    public bool CanJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.Length; ++i) {
            if (mx < i) {
                return false;
            }
            mx = Math.Max(mx, i + nums[i]);
        }
        return true;
    }
}