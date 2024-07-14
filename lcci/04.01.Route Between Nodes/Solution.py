class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        def dfs(i: int):
            if i == target:
                return True
            if i in vis:
                return False
            vis.add(i)
            return any(dfs(j) for j in g[i])

        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = set()
        return dfs(start)
