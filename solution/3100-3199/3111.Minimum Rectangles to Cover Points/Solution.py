class Solution:
    def minRectanglesToCoverPoints(self, points: List[List[int]], w: int) -> int:
        points.sort()
        ans, x1 = 0, -inf
        for x, _ in points:
            if x1 + w < x:
                x1 = x
                ans += 1
        return ans
