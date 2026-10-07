class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        mod = 10**9 + 7
        f = [[[0] * 16 for _ in range(7)] for _ in range(n + 1)]
        for j in range(7):
            for x in range(16):
                f[n][j][x] = 1
        for i in range(n - 1, -1, -1):
            for j in range(7):
                for x in range(16):
                    ans = 0
                    for k in range(1, 7):
                        if k != j:
                            ans += f[i + 1][k][1]
                        elif x < rollMax[j - 1]:
                            ans += f[i + 1][j][x + 1]
                    f[i][j][x] = ans % mod
        return f[0][0][0]
