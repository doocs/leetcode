class Solution:
    def countHousePlacements(self, n: int) -> int:
        mod = 10**9 + 7
        f = [[0] * 2 for _ in range(n)]
        f[0] = [1, 1]
        for i in range(1, n):
            f[i][0] = f[i - 1][0] + f[i - 1][1]
            f[i][1] = f[i - 1][0]
        s = sum(f[-1])
        return (s * s) % mod
