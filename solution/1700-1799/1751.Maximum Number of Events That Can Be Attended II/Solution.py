class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        events.sort(key=lambda x: x[1])
        n = len(events)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i, (st, _, val) in enumerate(events, 1):
            p = bisect_left(events, st, hi=i - 1, key=lambda x: x[1])
            for j in range(1, k + 1):
                f[i][j] = max(f[i - 1][j], f[p][j - 1] + val)
        return f[n][k]
