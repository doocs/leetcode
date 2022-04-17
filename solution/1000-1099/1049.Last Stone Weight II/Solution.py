class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        s = sum(stones)
        m, n = len(stones), s >> 1
        dp = [0] * (n + 1)
        for v in stones:
            for j in range(n, v - 1, -1):
                dp[j] = max(dp[j], dp[j - v] + v)
        return s - dp[-1] * 2
