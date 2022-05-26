class Solution:
    def nthUglyNumber(self, n: int) -> int:
        if n < 7:
            return n
        dp = [1 for _ in range(n)]
        i2 = i3 = i5 = 0
        for i in range(1, n):
            next2, next3, next5 = dp[i2] * 2, dp[i3] * 3, dp[i5] * 5
            dp[i] = min(next2, next3, next5)
            if dp[i] == next2:
                i2 += 1
            if dp[i] == next3:
                i3 += 1
            if dp[i] == next5:
                i5 += 1
        return dp[n - 1]
