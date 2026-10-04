class Solution:
    def countRestrictedPaths(self, n: int, edges: List[List[int]]) -> int:
        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [inf] * (n + 1)
        dist[n] = 0
        q = [(0, n)]
        mod = 10**9 + 7
        while q:
            _, u = heappop(q)
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        arr = list(range(1, n + 1))
        arr.sort(key=lambda i: dist[i])
        f = [0] * (n + 1)
        f[n] = 1
        for i in arr:
            for j, _ in g[i]:
                if dist[i] > dist[j]:
                    f[i] = (f[i] + f[j]) % mod
        return f[1]
