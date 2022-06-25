class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        dp = [0] * (k + 1)
        for s in presum:
            for j in range(k, -1, -1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[j] = max(dp[j], dp[j - idx] + v)
        return dp[-1]
