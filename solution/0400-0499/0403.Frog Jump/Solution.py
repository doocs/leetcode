class Solution:
    def canCross(self, stones: List[int]) -> bool:
        n = len(stones)
        dp = [[False] * n for i in range(n)]
        dp[0][0] = True
        for i in range(1, n):
            for j in range(i):
                k = stones[i] - stones[j]
                if k > j + 1:
                    continue
                dp[i][k] = dp[j][k - 1] or dp[j][k] or dp[j][k + 1]
                if i == n - 1 and dp[i][k]:
                    return True
        return False
