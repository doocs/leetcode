class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        def dfs(u, t):
            nonlocal ans, vis, d, next
            if vis[u]:
                return
            vis[u] = True
            for v in d[u]:
                dfs(v, t + 1)
            if ans < t:
                ans = t
                next = u

        d = defaultdict(set)
        vis = [False] * (len(edges) + 1)
        for u, v in edges:
            d[u].add(v)
            d[v].add(u)
        ans = 0
        next = 0
        dfs(edges[0][0], 0)
        vis = [False] * (len(edges) + 1)
        dfs(next, 0)
        return ans
