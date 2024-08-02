class Solution:
    def numberOfStableArrays(self, zero: int, one: int, limit: int) -> int:
        mod = 10**9 + 7
        f = [[[0, 0] for _ in range(one + 1)] for _ in range(zero + 1)]
        for i in range(1, min(limit, zero) + 1):
            f[i][0][0] = 1
        for j in range(1, min(limit, one) + 1):
            f[0][j][1] = 1
        for i in range(1, zero + 1):
            for j in range(1, one + 1):
                x = 0 if i - limit - 1 < 0 else f[i - limit - 1][j][1]
                y = 0 if j - limit - 1 < 0 else f[i][j - limit - 1][0]
                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1] - x) % mod
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1] - y) % mod
        return sum(f[zero][one]) % mod
