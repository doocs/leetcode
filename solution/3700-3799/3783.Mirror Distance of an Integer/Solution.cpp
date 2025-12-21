class Solution {
public:
    int mirrorDistance(int n) {
        auto reverse = [](int x) -> int {
            int y = 0;
            for (; x; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        return abs(n - reverse(n));
    }
};
