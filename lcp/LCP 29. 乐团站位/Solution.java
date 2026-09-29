class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        long n = num;
        long x = xPos;
        long y = yPos;

        long layer = Math.min(Math.min(x, y), Math.min(n - 1 - x, n - 1 - y));
        long side = n - 2 * layer;
        long start = ((4 * (layer % 9)) % 9 * ((n - layer) % 9)) % 9 + 1;

        long offset;
        if (x == layer) {
            offset = y - layer;
        } else if (y == n - layer - 1) {
            offset = side - 1 + x - layer;
        } else if (x == n - layer - 1) {
            offset = 2 * side - 2 + n - layer - 1 - y;
        } else {
            offset = 3 * side - 3 + n - layer - 1 - x;
        }

        return (int) ((start - 1 + offset) % 9 + 1);
    }
}