class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [[0] * (6 * n + 1) for _ in range(n + 1)]
        for j in range(1, 7):
            f[1][j] = 1
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if j - k >= 0:
                        f[i][j] += f[i - 1][j - k]
        m = pow(6, n)
        return [f[n][i] / m for i in range(n, 6 * n + 1)]
