class Solution:
    def maxScore(self, parent: list[int], op: list[int], score: list[int]) -> int:
        def dfs(i: int) -> int:
            if not g[i]:
                return score[i]
            tot = score[i]
            mn = inf
            for j in g[i]:
                t = dfs(j)
                mn = min(mn, t)
                tot += t
            if op[i] == 0:
                return tot - min(0, mn)
            return tot - mn

        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        return dfs(0)
