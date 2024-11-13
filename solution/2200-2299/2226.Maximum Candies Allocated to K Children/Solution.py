class Solution:
    def maximumCandies(self, candies: List[int], k: int) -> int:
        l, r = 0, max(candies)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(x // mid for x in candies) >= k:
                l = mid
            else:
                r = mid - 1
        return l
