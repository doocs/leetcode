class Solution:
    def numOfArrays(self, n: int, m: int, k: int) -> int:
        if k == 0:
            return 0
        mod = 10**9 + 7
        dp = [[0] * (m + 1) for _ in range(k + 1)]
        for j in range(1, m + 1):
            dp[1][j] = 1
        for i in range(2, n + 1):
            nxt = [[0] * (m + 1) for _ in range(k + 1)]
            for c in range(1, min(i, k) + 1):
                prefix = 0
                for j in range(1, m + 1):
                    prefix = (prefix + dp[c - 1][j - 1]) % mod
                    nxt[c][j] = (dp[c][j] * j + prefix) % mod
            dp = nxt
        return sum(dp[k]) % mod
