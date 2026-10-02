class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        next_buy = next_sell = next2_buy = 0
        for price in reversed(prices):
            buy = max(next_buy, -price + next_sell)
            sell = max(next_sell, price + next2_buy)
            next2_buy, next_buy, next_sell = next_buy, buy, sell
        return next_buy
