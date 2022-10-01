class Solution:
    def leadsToDestination(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        @cache
        def dfs(i):
            if i == destination:
                return not g[i]
            if i in vis or not g[i]:
                return False
            vis.add(i)
            for j in g[i]:
                if not dfs(j):
                    return False
            return True

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
        vis = set()
        return dfs(source)
