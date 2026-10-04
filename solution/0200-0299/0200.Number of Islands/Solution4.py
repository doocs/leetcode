class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def flood(i: int, j: int):
            stk = [(i, j)]
            grid[i][j] = '0'
            while stk:
                i, j = stk.pop()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':
                        grid[x][y] = '0'
                        stk.append((x, y))

        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    flood(i, j)
                    ans += 1
        return ans
