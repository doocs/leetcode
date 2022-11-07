class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        int t = 0, n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            int x = s - t - (n - i) * v + v * i - t;
            t += v;
            ans[i] = x;
        }
        return ans;
    }
}