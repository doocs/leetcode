class Solution:
    def distinctSequences(self, n: int) -> int:
        if n == 1:
            return 6
        mod = 10**9 + 7
        dp = [[[0] * 6 for _ in range(6)] for _ in range(n + 1)]
        for i in range(6):
            for j in range(6):
                if gcd(i + 1, j + 1) == 1 and i != j:
                    dp[2][i][j] = 1
        for k in range(3, n + 1):
            for i in range(6):
                for j in range(6):
                    if gcd(i + 1, j + 1) == 1 and i != j:
                        for h in range(6):
                            if gcd(h + 1, i + 1) == 1 and h != i and h != j:
                                dp[k][i][j] += dp[k - 1][h][i]
        ans = 0
        for i in range(6):
            for j in range(6):
                ans += dp[-1][i][j]
        return ans % mod
