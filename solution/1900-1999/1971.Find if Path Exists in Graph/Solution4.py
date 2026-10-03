class Solution:
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        if source == destination:
            return True
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        vis = [False] * n
        vis[source] = True
        stk = [source]
        while stk:
            i = stk.pop()
            for j in g[i]:
                if j == destination:
                    return True
                if not vis[j]:
                    vis[j] = True
                    stk.append(j)
        return False
