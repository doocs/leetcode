class Solution:
    def minSkips(self, dist: List[int], speed: int, hoursBefore: int) -> int:
        n = len(dist)
        dp = [[inf] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 0
        for i in range(1, n + 1):
            for j in range(i + 1):
                if i != j:
                    dp[i][j] = min(
                        dp[i][j],
                        ((dp[i - 1][j] + dist[i - 1] - 1) // speed + 1) * speed,
                    )
                if j > 0:
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1])
        for i in range(n + 1):
            if dp[n][i] <= hoursBefore * speed:
                return i
        return -1
