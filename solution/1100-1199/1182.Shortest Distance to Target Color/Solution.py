class Solution:
    def shortestDistanceColor(
        self, colors: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(colors)
        right = [[inf] * 3 for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(3):
                right[i][j] = right[i + 1][j]
            right[i][colors[i] - 1] = i
        left = [[-inf] * 3 for _ in range(n + 1)]
        for i, c in enumerate(colors, 1):
            for j in range(3):
                left[i][j] = left[i - 1][j]
            left[i][c - 1] = i - 1
        ans = []
        for i, c in queries:
            d = min(i - left[i + 1][c - 1], right[i][c - 1] - i)
            ans.append(-1 if d > n else d)
        return ans
