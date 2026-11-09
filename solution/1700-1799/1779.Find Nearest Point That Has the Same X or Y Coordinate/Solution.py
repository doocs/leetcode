class Solution:
    def nearestValidPoint(self, x: int, y: int, points: list[list[int]]) -> int:
        ans, mi = -1, inf
        for i, (a, b) in enumerate(points):
            if a == x or b == y:
                d = abs(a - x) + abs(b - y)
                if mi > d:
                    ans, mi = i, d
        return ans
