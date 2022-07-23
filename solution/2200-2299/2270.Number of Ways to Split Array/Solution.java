class Solution {
    public int waysToSplitArray(int[] nums) {
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        int ans = 0;
        long t = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            t += nums[i];
            if (t >= s - t) {
                ++ans;
            }
        }
        return ans;
    }
}