class Solution:
    def minRectanglesToCoverPoints(self, points: list[list[int]], w: int) -> int:
        points.sort()
        ans, x1 = 0, -1
        for x, _ in points:
            if x > x1:
                ans += 1
                x1 = x + w
        return ans
