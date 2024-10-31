class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[[0] * 2 for _ in range(k + 1)] for _ in range(n + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][0][1] = 1
        for i in range(1, n + 1):
            for j in range(k + 1):
                f[i][j][0] = (
                    (f[i - 1][j][1] + (f[i - 1][j - 1][0] if j else 0)) * cnt0 % mod
                )
                f[i][j][1] = (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod
        return sum(f[n][k]) % mod
