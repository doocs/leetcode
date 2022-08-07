class Solution:
    def secondMinimum(
        self, n: int, edges: List[List[int]], time: int, change: int
    ) -> int:
        g = defaultdict(set)
        for u, v in edges:
            g[u].add(v)
            g[v].add(u)
        q = deque([(1, 0)])
        dist = [[inf] * 2 for _ in range(n + 1)]
        dist[1][1] = 0
        while q:
            u, d = q.popleft()
            for v in g[u]:
                if d + 1 < dist[v][0]:
                    dist[v][0] = d + 1
                    q.append((v, d + 1))
                elif dist[v][0] < d + 1 < dist[v][1]:
                    dist[v][1] = d + 1
                    if v == n:
                        break
                    q.append((v, d + 1))
        ans = 0
        for i in range(dist[n][1]):
            ans += time
            if i < dist[n][1] - 1 and (ans // change) % 2 == 1:
                ans = (ans + change) // change * change
        return ans
