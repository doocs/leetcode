class Solution:
    def leadsToDestination(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        def dfs(i: int) -> bool:
            if st[i]:
                return st[i] == 2
            if not g[i]:
                return i == destination

            st[i] = 1
            for j in g[i]:
                if not dfs(j):
                    return False
            st[i] = 2
            return True

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
        if g[destination]:
            return False

        st = [0] * n
        return dfs(source)
