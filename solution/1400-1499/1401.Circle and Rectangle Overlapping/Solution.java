class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int a = f(x1, x2, xCenter);
        int b = f(y1, y2, yCenter);
        return a * a + b * b <= radius * radius;
    }

    private int f(int i, int j, int k) {
        if (i <= k && k <= j) {
            return 0;
        }
        return k < i ? i - k : k - j;
    }
}