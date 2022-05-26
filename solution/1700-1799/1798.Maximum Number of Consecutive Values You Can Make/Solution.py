class Solution:
    def getMaximumConsecutive(self, coins: List[int]) -> int:
        res = 1
        for coin in sorted(coins):
            if coin > res:
                break
            res += coin
        return res
