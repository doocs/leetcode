class Solution:
    def maxValue(self, events: List[List[int]], k: int) -> int:
        events.sort()
        n = len(events)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            _, ed, val = events[i]
            j = bisect_right(events, ed, lo=i + 1, key=lambda x: x[0])
            for c in range(k + 1):
                f[i][c] = f[i + 1][c]
                if c:
                    f[i][c] = max(f[i][c], f[j][c - 1] + val)
        return f[0][k]
