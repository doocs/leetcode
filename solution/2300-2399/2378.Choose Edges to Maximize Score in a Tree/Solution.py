class Solution:
    def maxScore(self, edges: List[List[int]]) -> int:
        def dfs(i):
            a = b = t = 0
            for j, w in g[i]:
                x, y = dfs(j)
                a += y
                b += y
                t = max(t, x - y + w)
            b += t
            return a, b

        g = defaultdict(list)
        for i, (p, w) in enumerate(edges[1:], 1):
            g[p].append((i, w))
        return dfs(0)[1]
