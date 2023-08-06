class Solution {
public:
    int accountBalanceAfterPurchase(int purchaseAmount) {
        int diff = 100, x = 0;
        for (int y = 100; y >= 0; y -= 10) {
            int t = abs(y - purchaseAmount);
            if (t < diff) {
                diff = t;
                x = y;
            }
        }
        return 100 - x;
    }
};