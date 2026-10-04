class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        cost = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            u, fa, state = stk.pop()
            if state == 0:
                stk.append((u, fa, 1))
                for v in g[u]:
                    if v != fa:
                        stk.append((v, u, 0))
            else:
                nxt = 0
                for v in g[u]:
                    if v != fa:
                        nxt += cost[v]
                if hasApple[u] or nxt:
                    cost[u] = nxt if u == 0 else nxt + 2
        return cost[0]
