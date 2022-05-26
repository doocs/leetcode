class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        t = [(s.count('0'), s.count('1')) for s in strs]
        for k in range(len(strs)):
            n0, n1 = t[k]
            for i in range(m, n0 - 1, -1):
                for j in range(n, n1 - 1, -1):
                    dp[i][j] = max(dp[i][j], dp[i - n0][j - n1] + 1)
        return dp[-1][-1]
