class Solution:
    def maximalPathQuality(
        self, values: List[int], edges: List[List[int]], maxTime: int
    ) -> int:
        def dfs(u: int, cost: int, value: int):
            if u == 0:
                nonlocal ans
                ans = max(ans, value)
            for v, t in g[u]:
                if cost + t <= maxTime:
                    if vis[v]:
                        dfs(v, cost + t, value)
                    else:
                        vis[v] = True
                        dfs(v, cost + t, value + values[v])
                        vis[v] = False

        n = len(values)
        g = [[] for _ in range(n)]
        for u, v, t in edges:
            g[u].append((v, t))
            g[v].append((u, t))
        vis = [False] * n
        vis[0] = True
        ans = 0
        dfs(0, 0, values[0])
        return ans
