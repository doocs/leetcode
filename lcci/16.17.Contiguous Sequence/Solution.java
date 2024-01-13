class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE, f = Integer.MIN_VALUE;
        for (int x : nums) {
            f = Math.max(f, 0) + x;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}