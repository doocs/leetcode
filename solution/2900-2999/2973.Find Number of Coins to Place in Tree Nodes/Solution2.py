class Solution:
    def placedCoins(self, edges: List[List[int]], cost: List[int]) -> List[int]:
        n = len(cost)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = [1] * n
        sub = [None] * n
        stk = [(0, -1, 0)]
        while stk:
            a, fa, state = stk.pop()
            if state == 0:
                stk.append((a, fa, 1))
                for b in g[a]:
                    if b != fa:
                        stk.append((b, a, 0))
            else:
                res = [cost[a]]
                for b in g[a]:
                    if b != fa:
                        res.extend(sub[b])
                res.sort()
                if len(res) >= 3:
                    ans[a] = max(
                        res[-3] * res[-2] * res[-1], res[0] * res[1] * res[-1], 0
                    )
                if len(res) > 5:
                    res = res[:2] + res[-3:]
                sub[a] = res
        return ans
