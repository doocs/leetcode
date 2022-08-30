class Solution:
    def maxScore(self, edges: List[List[int]]) -> int:
        def dfs(i):
            a = b = 0
            t = 0
            for j in g[i]:
                x, y = dfs(j)
                a += y
                b += y
                t = max(t, x - y + g[i][j])
            b += t
            return a, b

        g = defaultdict(lambda: defaultdict(lambda: -inf))
        for i, (p, w) in enumerate(edges[1:], 1):
            g[p][i] = w
        return max(dfs(0))
