class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        arr = [(a, b - a) for a, b in zip(present, future) if b > a]
        dp = [0] * (budget + 1)
        for v, w in arr:
            for j in range(budget, v - 1, -1):
                dp[j] = max(dp[j], dp[j - v] + w)
        return dp[-1]
