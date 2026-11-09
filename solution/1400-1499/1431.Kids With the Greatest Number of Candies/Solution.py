class Solution:
    def kidsWithCandies(self, candies: list[int], extraCandies: int) -> list[bool]:
        mx = max(candies)
        return [candy + extraCandies >= mx for candy in candies]
