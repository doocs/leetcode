class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int v : nums) {
            cnt = v != 0 ? 0 : cnt + 1;
            ans += cnt;
        }
        return ans;
    }
}