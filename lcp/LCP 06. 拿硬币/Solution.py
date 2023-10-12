class Solution:
    def minCount(self, coins: List[int]) -> int:
        return sum((x + 1) >> 1 for x in coins)
