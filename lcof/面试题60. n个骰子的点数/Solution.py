class Solution:
    def twoSum(self, n: int) -> List[float]:
        dp = [[0 for _ in range(6 * n + 1)] for _ in range(n + 1)]
        for j in range(1, 7):
            dp[1][j] = 1
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if j <= k:
                        break
                    dp[i][j] += dp[i - 1][j - k]
        res, total = [], pow(6, n)
        for i in range(5 * n + 1):
            res.append(dp[n][n + i] / total)
        return res
