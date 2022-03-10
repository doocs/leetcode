class Solution:
    def knightProbability(self, n: int, k: int, row: int, column: int) -> float:
        dp = [[[0] * n for _ in range(n)] for _ in range(k + 1)]
        for l in range(k + 1):
            for i in range(n):
                for j in range(n):
                    if l == 0:
                        dp[l][i][j] = 1
                    else:
                        for a, b in (
                            (-2, -1),
                            (-2, 1),
                            (2, -1),
                            (2, 1),
                            (-1, -2),
                            (-1, 2),
                            (1, -2),
                            (1, 2),
                        ):
                            x, y = i + a, j + b
                            if 0 <= x < n and 0 <= y < n:
                                dp[l][i][j] += dp[l - 1][x][y] / 8
        return dp[k][row][column]
