class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        events.sort(key=lambda x: x[1])
        n = len(events)
        dp = [[0] * (k + 1) for _ in range(n + 1)]
        for i, (s, _, v) in enumerate(events):
            h = bisect_left(events, s, hi=i, key=lambda x: x[1])
            for j in range(1, k + 1):
                dp[i + 1][j] = max(dp[i][j], dp[h][j - 1] + v)
        return dp[n][k]
