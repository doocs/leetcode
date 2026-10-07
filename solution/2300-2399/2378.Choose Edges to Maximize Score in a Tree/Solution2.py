class Solution:
    def maxScore(self, edges: List[List[int]]) -> int:
        n = len(edges)
        g = [[] for _ in range(n)]
        for i, (p, w) in enumerate(edges[1:], 1):
            g[p].append((i, w))
        down = [(0, 0)] * n
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j, _ in g[i]:
                    stk.append((j, 0))
            else:
                a = b = t = 0
                for j, w in g[i]:
                    x, y = down[j]
                    a += y
                    b += y
                    t = max(t, x - y + w)
                b += t
                down[i] = (a, b)
        return down[0][1]
