class Solution:
    def maxCoins(self, piles: list[int]) -> int:
        piles.sort()
        return sum(piles[len(piles) // 3 :][::2])
