class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def farthest(start: int) -> (int, int):
            ans = 0
            node = start
            stk = [(start, -1, 0)]
            while stk:
                i, fa, t = stk.pop()
                if ans < t:
                    ans = t
                    node = i
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, t + 1))
            return node, ans

        node, _ = farthest(0)
        return farthest(node)[1]
