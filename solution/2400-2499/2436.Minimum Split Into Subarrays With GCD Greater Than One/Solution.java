class Solution {
    public int minimumSplits(int[] nums) {
        int ans = 1, x = nums[0];
        for (int v : nums) {
            x = gcd(x, v);
            if (x == 1) {
                x = v;
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}