class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            for (int y : nums) {
                if (Math.abs(x - y) <= Math.min(x, y)) {
                    ans = Math.max(ans, x ^ y);
                }
            }
        }
        return ans;
    }
}