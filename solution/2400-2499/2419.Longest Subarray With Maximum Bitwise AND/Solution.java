class Solution {
    public int longestSubarray(int[] nums) {
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
        }
        int ans = 0, cnt = 0;
        for (int v : nums) {
            if (v == mx) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}