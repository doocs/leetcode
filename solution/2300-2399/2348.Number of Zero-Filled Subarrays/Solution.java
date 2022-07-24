class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int v : nums) {
            if (v == 0) {
                ++cnt;
            } else {
                ans += (long) (1 + cnt) * cnt / 2;
                cnt = 0;
            }
        }
        ans += (long) (1 + cnt) * cnt / 2;
        return ans;
    }
}