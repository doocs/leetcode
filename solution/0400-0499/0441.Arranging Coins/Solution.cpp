class Solution {
public:
    int arrangeCoins(int n) {
        return (int) (sqrt(2) * sqrt(n + 0.125) - 0.5);
    }
};
