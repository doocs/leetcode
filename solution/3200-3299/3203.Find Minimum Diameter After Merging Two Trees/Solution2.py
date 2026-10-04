class Solution:
    def minimumDiameterAfterMerge(
        self, edges1: List[List[int]], edges2: List[List[int]]
    ) -> int:
        d1 = self.treeDiameter(edges1)
        d2 = self.treeDiameter(edges2)
        return max(d1, d2, (d1 + 1) // 2 + (d2 + 1) // 2 + 1)

    def treeDiameter(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def farthest(start: int) -> tuple:
            ans, node = 0, start
            stk = [(start, -1, 0)]
            while stk:
                i, fa, t = stk.pop()
                if ans < t:
                    ans = t
                    node = i
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, t + 1))
            return ans, node

        _, a = farthest(0)
        ans, _ = farthest(a)
        return ans
