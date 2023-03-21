class Solution {
    public int maximumDifference(int[] nums) {
        int mi = 1 << 30;
        int ans = -1;
        for (int x : nums) {
            if (x > mi) {
                ans = Math.max(ans, x - mi);
            } else {
                mi = x;
            }
        }
        return ans;
    }
}