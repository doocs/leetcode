class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        points.sort(key=lambda x: x[1])
        ans = 1
        x = points[0][1]
        for a, b in points:
            if a > x:
                ans += 1
                x = b
        return ans
