class Solution:
    def matrixScore(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        for i in range(m):
            if grid[i][0] == 0:
                for j in range(n):
                    grid[i][j] ^= 1

        res = 0
        for j in range(n):
            cnt = 0
            for i in range(m):
                cnt += grid[i][j]
            res += max(cnt, m - cnt) * (1 << (n - j - 1))
        return res
