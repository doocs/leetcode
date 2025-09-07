class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt = 0;
        for (int x : nums) {
            if (x == 0) {
                ans += ++cnt;
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}
