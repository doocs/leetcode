class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        def dfs(a: int):
            vis[a] = True
            nonlocal ans
            for b, w in g[a]:
                ans = min(ans, w)
                if not vis[b]:
                    dfs(b)

        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = inf
        vis = [False] * (n + 1)
        dfs(1)
        return ans
