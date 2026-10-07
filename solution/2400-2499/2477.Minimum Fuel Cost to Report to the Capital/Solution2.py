class Solution:
    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        n = len(roads) + 1
        g = [[] for _ in range(n)]
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        sz = [1] * n
        stk = [(0, -1, 0)]
        while stk:
            a, fa, state = stk.pop()
            if state == 0:
                stk.append((a, fa, 1))
                for b in g[a]:
                    if b != fa:
                        stk.append((b, a, 0))
            else:
                for b in g[a]:
                    if b != fa:
                        t = sz[b]
                        ans += (t + seats - 1) // seats
                        sz[a] += t
        return ans
