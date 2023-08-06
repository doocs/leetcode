class Solution:
    def accountBalanceAfterPurchase(self, purchaseAmount: int) -> int:
        diff, x = 100, 0
        for y in range(100, -1, -10):
            if (t := abs(y - purchaseAmount)) < diff:
                diff = t
                x = y
        return 100 - x
