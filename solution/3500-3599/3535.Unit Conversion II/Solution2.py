class Solution:
    def queryConversions(
        self, conversions: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        res = [0] * n
        stk = [(0, 1)]
        while stk:
            s, mul = stk.pop()
            res[s] = mul
            for t, w in g[s]:
                stk.append((t, mul * w % mod))
        ans = []
        for x, y in queries:
            ans.append(res[y] * pow(res[x], mod - 2, mod) % mod)
        return ans
