class Solution:
    def numWays(self, n: int, relation: List[List[int]], k: int) -> int:
        dp = [[0] * n for _ in range(k + 1)]
        dp[0][0] = 1
        for i in range(1, k + 1):
            for a, b in relation:
                dp[i][b] += dp[i - 1][a]
        return dp[-1][-1]
