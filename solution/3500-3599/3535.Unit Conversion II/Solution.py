class Solution:
    def queryConversions(
        self, conversions: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        def dfs(s: int, mul: int) -> None:
            res[s] = mul
            for t, w in g[s]:
                dfs(t, mul * w % mod)

        mod = 10**9 + 7
        n = len(conversions) + 1
        g = [[] for _ in range(n)]
        for s, t, w in conversions:
            g[s].append((t, w))
        res = [0] * n
        dfs(0, 1)
        ans = []
        for x, y in queries:
            ans.append(res[y] * pow(res[x], mod - 2, mod) % mod)
        return ans
