class Solution:
    def maxScore(self, grid: List[List[int]]) -> int:
        g = defaultdict(set)
        mx = 0
        for i, row in enumerate(grid):
            for x in row:
                g[x].add(i)
                mx = max(mx, x)
        m = len(grid)
        f = [[0] * (1 << m) for _ in range(mx + 1)]
        for i in range(1, mx + 1):
            for j in range(1 << m):
                f[i][j] = f[i - 1][j]
                for k in g[i]:
                    if j >> k & 1:
                        f[i][j] = max(f[i][j], f[i - 1][j ^ 1 << k] + i)
        return f[-1][-1]
