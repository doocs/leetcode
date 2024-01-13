class Solution:
    def waysToChange(self, n: int) -> int:
        mod = 10**9 + 7
        coins = [25, 10, 5, 1]
        f = [1] + [0] * n
        for c in coins:
            for j in range(c, n + 1):
                f[j] = (f[j] + f[j - c]) % mod
        return f[n]
