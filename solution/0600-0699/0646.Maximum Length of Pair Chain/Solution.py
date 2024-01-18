class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        pairs.sort()
        dp = [1] * len(pairs)
        for i, (c, _) in enumerate(pairs):
            for j, (_, b) in enumerate(pairs[:i]):
                if b < c:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
