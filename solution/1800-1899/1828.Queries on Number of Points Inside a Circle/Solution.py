class Solution:
    def countPoints(
        self, points: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for x0, y0, r in queries:
            count = 0
            for x, y in points:
                dx, dy = x - x0, y - y0
                if dx * dx + dy * dy <= r * r:
                    count += 1
            ans.append(count)
        return ans
