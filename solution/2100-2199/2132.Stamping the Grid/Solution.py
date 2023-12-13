class Solution:
    def possibleToStamp(
        self, grid: List[List[int]], stampHeight: int, stampWidth: int
    ) -> bool:
        m, n = len(grid), len(grid[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + v
        d = [[0] * (n + 2) for _ in range(m + 2)]
        for i in range(1, m - stampHeight + 2):
            for j in range(1, n - stampWidth + 2):
                x, y = i + stampHeight - 1, j + stampWidth - 1
                if s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] == 0:
                    d[i][j] += 1
                    d[i][y + 1] -= 1
                    d[x + 1][j] -= 1
                    d[x + 1][y + 1] += 1
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1]
                if v == 0 and d[i][j] == 0:
                    return False
        return True
