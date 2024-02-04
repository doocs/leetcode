class Solution {
    public int returnToBoundaryCount(int[] nums) {
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x;
            if (s == 0) {
                ++ans;
            }
        }
        return ans;
    }
}