class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        dp = [False] * n
        dp[0] = True
        pre_sum = [0] * (n + 1)
        pre_sum[1] = 1
        for i in range(1, n):
            if s[i] == '0':
                l = max(0, i - maxJump)
                r = i - minJump
                if r >= l and pre_sum[r + 1] - pre_sum[l] > 0:
                    dp[i] = True
            pre_sum[i + 1] = pre_sum[i] + dp[i]
        return dp[n - 1]
