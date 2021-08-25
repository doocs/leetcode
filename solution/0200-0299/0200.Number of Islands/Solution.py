class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        p = [-1] * (m * n)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    p[i * n + j] = i * n + j

        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    if i < m - 1 and grid[i + 1][j] == '1':
                        a, b = i * n + j, (i + 1) * n + j
                        p[find(a)] = find(b)
                    if j < n - 1 and grid[i][j + 1] == '1':
                        a, b = i * n + j, i * n + j + 1
                        p[find(a)] = find(b)
        
        cnt = 0
        for i in range(m):
            for j in range(n):
                if i * n + j == find(i * n + j):
                    cnt += 1
        return cnt
