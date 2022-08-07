class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        g = defaultdict(list)
        vis = [False] * n
        for v in restricted:
            vis[v] = True
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def dfs(u):
            nonlocal ans
            if vis[u]:
                return
            ans += 1
            vis[u] = True
            for v in g[u]:
                dfs(v)

        ans = 0
        dfs(0)
        return ans
