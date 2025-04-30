class Solution:
    def baseUnitConversions(self, conversions: List[List[int]]) -> List[int]:
        def dfs(s: int, mul: int) -> None:
            ans[s] = mul
            for t, w in g[s]:
                dfs(t, mul * w % mod)

        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        ans = [0] * n
        dfs(0, 1)
        return ans
