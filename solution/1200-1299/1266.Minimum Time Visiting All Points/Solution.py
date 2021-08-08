class Solution:
    def minTimeToVisitAllPoints(self, points: List[List[int]]) -> int:
        res = 0
        x0, y0 = points[0][0], points[0][1]
        for x1, y1 in points[1:]:
            res += max(abs(x0 - x1), abs(y0 - y1))
            x0, y0 = x1, y1
        return res
