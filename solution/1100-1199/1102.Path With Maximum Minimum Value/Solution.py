class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        ans = min(grid[0][0], grid[-1][-1])
        vis = {(0, 0), (m - 1, n - 1)}
        scores = [[grid[i][j], i, j] for i in range(m) for j in range(n)]
        scores.sort()
        while find(0) != find(m * n - 1):
            score, i, j = scores.pop()
            ans = min(ans, score)
            vis.add((i, j))
            for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and (x, y) in vis:
                    p[find(x * n + y)] = find(i * n + j)
        return ans
