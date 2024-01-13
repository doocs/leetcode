class Solution:
    def waysToChange(self, n: int) -> int:
        mod = 10**9 + 7
        coins = [25, 10, 5, 1]
        f = [[0] * (n + 1) for _ in range(5)]
        f[0][0] = 1
        for i, c in enumerate(coins, 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j >= c:
                    f[i][j] = (f[i][j] + f[i][j - c]) % mod
        return f[-1][n]
