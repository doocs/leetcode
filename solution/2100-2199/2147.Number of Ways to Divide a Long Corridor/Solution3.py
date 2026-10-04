class Solution:
    def numberOfWays(self, corridor: str) -> int:
        mod = 10**9 + 7
        n = len(corridor)
        f = [[0, 0, 0] for _ in range(n + 1)]
        f[n][2] = 1
        for i in range(n - 1, -1, -1):
            for k in range(3):
                nk = k + (corridor[i] == "S")
                if nk > 2:
                    continue
                f[i][k] = f[i + 1][nk]
                if nk == 2:
                    f[i][k] = (f[i][k] + f[i + 1][0]) % mod
        return f[0][0]
