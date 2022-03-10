class Solution:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        for i in range(m):
            for j in range(n):
                for a, b in [[0, 1], [1, 0]]:
                    x, y = i + a, j + b
                    if x < m and y < n and grid[x][y] == grid[i][j]:
                        if find(x * n + y) == find(i * n + j):
                            return True
                        p[find(x * n + y)] = find(i * n + j)
        return False
