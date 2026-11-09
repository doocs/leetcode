class Solution:
    def countPoints(
        self, points: list[list[int]], queries: list[list[int]]
    ) -> list[int]:
        ans = []
        for x, y, r in queries:
            cnt = 0
            for i, j in points:
                dx, dy = i - x, j - y
                cnt += dx * dx + dy * dy <= r * r
            ans.append(cnt)
        return ans
