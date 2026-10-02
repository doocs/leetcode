class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        cash = [0] * (k + 1)
        hold = [-inf] * (k + 1)
        for price in prices:
            for transactions in range(1, k + 1):
                hold[transactions] = max(
                    hold[transactions], cash[transactions - 1] - price
                )
                cash[transactions] = max(cash[transactions], hold[transactions] + price)
        return cash[k]
