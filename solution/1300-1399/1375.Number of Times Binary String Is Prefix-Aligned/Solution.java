class Solution {
    public int numTimesAllBlue(int[] flips) {
        int ans = 0;
        int mx = 0;
        for (int i = 1; i <= flips.length; ++i) {
            mx = Math.max(mx, flips[i - 1]);
            if (mx == i) {
                ++ans;
            }
        }
        return ans;
    }
}