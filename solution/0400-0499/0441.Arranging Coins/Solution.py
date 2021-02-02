class Solution:
    def arrangeCoins(self, n: int) -> int:
        return int(math.sqrt(2) * math.sqrt(n + 0.125) - 0.5)
