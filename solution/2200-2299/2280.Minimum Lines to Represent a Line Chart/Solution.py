class Solution:
    def minimumLines(self, stockPrices: List[List[int]]) -> int:
        stockPrices.sort()
        dx, dy = 0, 1
        ans = 0
        for (x, y), (x1, y1) in pairwise(stockPrices):
            dx1, dy1 = x1 - x, y1 - y
            if dy * dx1 != dx * dy1:
                ans += 1
            dx, dy = dx1, dy1
        return ans
