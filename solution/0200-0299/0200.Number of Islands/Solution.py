class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    ans += 1
                    grid[i][j] = '0'
                    stack = [(i, j)]
                    while stack:
                        x, y = stack.pop()
                        for a, b in pairwise(dirs):
                            u, v = x + a, y + b
                            if 0 <= u < m and 0 <= v < n and grid[u][v] == '1':
                                grid[u][v] = '0'
                                stack.append((u, v))
        return ans
