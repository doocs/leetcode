class Solution:
    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        points.sort(key=lambda p: hypot(p[0], p[1]))
        return points[:k]
