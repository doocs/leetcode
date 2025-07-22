class Solution:
    def maxTargetNodes(
        self, edges1: List[List[int]], edges2: List[List[int]]
    ) -> List[int]:
        def build(edges: List[List[int]]) -> List[List[int]]:
            n = len(edges) + 1
            g = [[] for _ in range(n)]
            for a, b in edges:
                g[a].append(b)
                g[b].append(a)
            return g

        def dfs(
            g: List[List[int]], a: int, fa: int, c: List[int], d: int, cnt: List[int]
        ):
            c[a] = d
            cnt[d] += 1
            for b in g[a]:
                if b != fa:
                    dfs(g, b, a, c, d ^ 1, cnt)

        g1 = build(edges1)
        g2 = build(edges2)
        n, m = len(g1), len(g2)
        c1 = [0] * n
        c2 = [0] * m
        cnt1 = [0, 0]
        cnt2 = [0, 0]
        dfs(g2, 0, -1, c2, 0, cnt2)
        dfs(g1, 0, -1, c1, 0, cnt1)
        t = max(cnt2)
        return [t + cnt1[c1[i]] for i in range(n)]
