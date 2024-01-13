class Solution {
    public long countSubarrays(int[] nums) {
        long ans = 0;
        int pre = 0, cnt = 0;
        for (int x : nums) {
            if (pre < x) {
                ++cnt;
            } else {
                cnt = 1;
            }
            pre = x;
            ans += cnt;
        }
        return ans;
    }
}