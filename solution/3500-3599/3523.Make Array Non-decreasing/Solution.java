class Solution {
    public int maximumPossibleSize(int[] nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            if (mx <= x) {
                ++ans;
                mx = x;
            }
        }
        return ans;
    }
}