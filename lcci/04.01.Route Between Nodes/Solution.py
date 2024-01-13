class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        def dfs(u):
            if u == target:
                return True
            for v in g[u]:
                if v not in vis:
                    vis.add(v)
                    if dfs(v):
                        return True
            return False

        g = defaultdict(list)
        for u, v in graph:
            g[u].append(v)
        vis = {start}
        return dfs(start)
