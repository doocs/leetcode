class Solution {
public:
    int orchestraLayout(int num, int xPos, int yPos) {
        long long n = num;
        long long x = xPos;
        long long y = yPos;

        long long layer = min(min(x, y), min(n - 1 - x, n - 1 - y));
        long long side = n - 2 * layer;
        long long start = ((4 * (layer % 9)) % 9 * ((n - layer) % 9)) % 9 + 1;

        long long offset;
        if (x == layer) {
            offset = y - layer;
        } else if (y == n - layer - 1) {
            offset = side - 1 + x - layer;
        } else if (x == n - layer - 1) {
            offset = 2 * side - 2 + n - layer - 1 - y;
        } else {
            offset = 3 * side - 3 + n - layer - 1 - x;
        }

        return (start - 1 + offset) % 9 + 1;
    }
};