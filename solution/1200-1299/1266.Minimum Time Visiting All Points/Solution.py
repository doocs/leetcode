class Solution:
    def minTimeToVisitAllPoints(self, points: list[list[int]]) -> int:
        return sum(
            max(abs(p1[0] - p2[0]), abs(p1[1] - p2[1])) for p1, p2 in pairwise(points)
        )
