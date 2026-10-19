class Solution:
    def findColoredRoot(self, edges: list[list[int]], colors: str) -> int:
        d = {"W": 0, "R": 1, "B": 2}
        n = len(colors)
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        ans = -1
        for u, vs in enumerate(g):
            if len(vs) > 3:
                return -1
            cnt = [0] * 3
            for v in vs:
                cnt[d[colors[v]]] += 1
            c = d[colors[u]]
            if cnt[c] > 0 or cnt[(c + 2) % 3] > 1:
                return -1
            if len(vs) < 3 and cnt[(c + 2) % 3] == 0:
                ans = u
        return ans
