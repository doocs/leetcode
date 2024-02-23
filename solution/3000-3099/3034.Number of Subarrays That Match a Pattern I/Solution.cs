public class Solution {
    public int CountMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.Length, m = pattern.Length;
        int ans = 0;
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }

    private int f(int a, int b) {
        return a == b ? 0 : (a < b ? 1 : -1);
    }
}