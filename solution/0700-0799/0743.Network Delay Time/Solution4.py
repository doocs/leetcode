class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3F3F
        dist = [INF] * n
        vis = [False] * n
        g = defaultdict(list)
        for u, v, w in times:
            g[u - 1].append((v - 1, w))
        k -= 1
        dist[k] = 0
        q = deque([k])
        vis[k] = True
        while q:
            u = q.popleft()
            vis[u] = False
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    if not vis[v]:
                        q.append(v)
                        vis[v] = True
        ans = max(dist)
        return -1 if ans == INF else ans
