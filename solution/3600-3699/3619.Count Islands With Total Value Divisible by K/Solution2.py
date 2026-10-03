class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        def flood(i: int, j: int) -> int:
            s = grid[i][j]
            grid[i][j] = 0
            stk = [(i, j)]
            while stk:
                i, j = stk.pop()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y]:
                        s += grid[x][y]
                        grid[x][y] = 0
                        stk.append((x, y))
            return s

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] and flood(i, j) % k == 0:
                    ans += 1
        return ans
