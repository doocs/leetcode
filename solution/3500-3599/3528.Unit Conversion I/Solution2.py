class Solution:
    def baseUnitConversions(self, conversions: List[List[int]]) -> List[int]:
        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        ans = [0] * n
        stk = [(0, 1)]
        while stk:
            s, mul = stk.pop()
            ans[s] = mul
            for t, w in g[s]:
                stk.append((t, mul * w % mod))
        return ans
