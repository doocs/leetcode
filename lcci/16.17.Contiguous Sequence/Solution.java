class Solution {
    public int maxSubArray(int[] nums) {
        int inf = Integer.MIN_VALUE;
        int ans = inf, s = inf;
        for (int v : nums) {
            s = Math.max(s, 0) + v;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}