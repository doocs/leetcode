class Solution:
    def maxWidthOfVerticalArea(self, points: list[list[int]]) -> int:
        points.sort()
        return max(b[0] - a[0] for a, b in pairwise(points))
