class Solution:
    def countRestrictedPaths(self, n: int, edges: List[List[int]]) -> int:
        @cache
        def dfs(i):
            if i == n:
                return 1
            ans = 0
            for j, _ in g[i]:
                if dist[i] > dist[j]:
                    ans = (ans + dfs(j)) % mod
            return ans

        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        q = [(0, n)]
        dist = [inf] * (n + 1)
        dist[n] = 0
        mod = 10**9 + 7
        while q:
            _, u = heappop(q)
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        return dfs(1)
