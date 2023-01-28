public class Solution {
    public int[] GetSumAbsoluteDifferences(int[] nums) {
        int s = 0, t = 0;
        foreach (int x in nums) {
            s += x;
        }
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
}