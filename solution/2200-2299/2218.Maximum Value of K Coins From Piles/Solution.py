class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        n = len(piles)
        dp = [[0] * (k + 1) for _ in range(n + 1)]
        for i, s in enumerate(presum, 1):
            for j in range(k + 1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[i][j] = max(dp[i][j], dp[i - 1][j - idx] + v)
        return dp[-1][-1]
