class Solution:
    def getKthMagicNumber(self, k: int) -> int:
        dp = [1] * (k + 1)
        p3 = p5 = p7 = 1
        for i in range(2, k + 1):
            a, b, c = dp[p3] * 3, dp[p5] * 5, dp[p7] * 7
            v = min(a, b, c)
            dp[i] = v
            if v == a:
                p3 += 1
            if v == b:
                p5 += 1
            if v == c:
                p7 += 1
        return dp[k]
