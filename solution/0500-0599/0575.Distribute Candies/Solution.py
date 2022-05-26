class Solution:
    def distributeCandies(self, candyType: List[int]) -> int:
        return min(len(candyType) >> 1, len(set(candyType)))
