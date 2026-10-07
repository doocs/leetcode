class Solution:
    def checkRecord(self, n: int) -> int:
        mod = 10**9 + 7
        f = [[1] * 3 for _ in range(2)]
        for _ in range(n):
            g = [[0] * 3 for _ in range(2)]
            for j in range(2):
                for k in range(3):
                    ans = f[j][0]
                    if j == 0:
                        ans += f[1][0]
                    if k < 2:
                        ans += f[j][k + 1]
                    g[j][k] = ans % mod
            f = g
        return f[0][0]
