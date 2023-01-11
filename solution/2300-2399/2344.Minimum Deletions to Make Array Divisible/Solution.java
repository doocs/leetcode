class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int x = 0;
        for (int v : numsDivide) {
            x = gcd(x, v);
        }
        int y = 1 << 30;
        for (int v : nums) {
            if (x % v == 0) {
                y = Math.min(y, v);
            }
        }
        if (y == 1 << 30) {
            return -1;
        }
        int ans = 0;
        for (int v : nums) {
            if (v < y) {
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}