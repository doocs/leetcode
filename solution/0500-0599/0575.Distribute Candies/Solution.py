class Solution:
    def distributeCandies(self, candyType: list[int]) -> int:
        return min(len(candyType) >> 1, len(set(candyType)))
