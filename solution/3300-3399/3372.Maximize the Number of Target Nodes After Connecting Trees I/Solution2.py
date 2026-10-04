class Solution:
    def maxTargetNodes(
        self, edges1: List[List[int]], edges2: List[List[int]], k: int
    ) -> List[int]:
        def build(edges: List[List[int]]) -> List[List[int]]:
            n = len(edges) + 1
            g = [[] for _ in range(n)]
            for a, b in edges:
                g[a].append(b)
                g[b].append(a)
            return g

        def dfs(g: List[List[int]], a: int, fa: int, d: int) -> int:
            if d < 0:
                return 0
            stk = [(a, fa, d)]
            cnt = 0
            while stk:
                u, p, rem = stk.pop()
                cnt += 1
                if rem == 0:
                    continue
                for b in g[u]:
                    if b != p:
                        stk.append((b, u, rem - 1))
            return cnt

        g2 = build(edges2)
        m = len(edges2) + 1
        t = max(dfs(g2, i, -1, k - 1) for i in range(m))
        g1 = build(edges1)
        n = len(edges1) + 1
        return [dfs(g1, i, -1, k) + t for i in range(n)]
