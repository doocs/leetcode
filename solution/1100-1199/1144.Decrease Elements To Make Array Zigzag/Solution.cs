public class Solution {
    public int MovesToMakeZigzag(int[] nums) {
        int[] ans = new int[2];
        int n = nums.Length;
        for (int i = 0; i < 2; ++i) {
            for (int j = i; j < n; j += 2) {
                int d = 0;
                if (j > 0) {
                    d = Math.Max(d, nums[j] - nums[j - 1] + 1);
                }
                if (j < n - 1) {
                    d = Math.Max(d, nums[j] - nums[j + 1] + 1);
                }
                ans[i] += d;
            }
        }
        return Math.Min(ans[0], ans[1]);
    }
}