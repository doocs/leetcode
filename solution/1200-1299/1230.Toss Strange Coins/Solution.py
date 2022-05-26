class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        dp = [0] * (target + 1)
        dp[0] = 1
        for v in prob:
            for j in range(target, -1, -1):
                dp[j] *= 1 - v
                if j >= 1:
                    dp[j] += dp[j - 1] * v
        return dp[-1]
