class Solution {
    public int countTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            for (int y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
                }
            }
        }
        return ans;
    }
}