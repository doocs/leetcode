class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3F3F
        dist = [INF] * n
        dist[k - 1] = 0
        for _ in range(n):
            backup = dist[:]
            for u, v, w in times:
                dist[v - 1] = min(dist[v - 1], dist[u - 1] + w)
        ans = max(dist)
        return -1 if ans == INF else ans
