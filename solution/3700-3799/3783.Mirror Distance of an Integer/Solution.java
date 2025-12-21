class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    private int reverse(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
