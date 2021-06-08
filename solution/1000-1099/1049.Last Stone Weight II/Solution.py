class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        s = sum(stones)
        n = s // 2
        dp = [False for i in range(n + 1)]
        dp[0] = True
        for stone in stones:
            for j in range(n, stone - 1, -1):
                dp[j] = dp[j] or dp[j - stone]
        for j in range(n, -1, -1):
            if dp[j]:
                return s - j - j
