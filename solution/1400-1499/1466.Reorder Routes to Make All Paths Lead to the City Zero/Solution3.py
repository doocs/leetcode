class Solution:
    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in connections:
            g[a].append((b, 1))
            g[b].append((a, 0))
        ans = 0
        stk = [(0, -1)]
        while stk:
            a, fa = stk.pop()
            for b, c in g[a]:
                if b != fa:
                    ans += c
                    stk.append((b, a))
        return ans
