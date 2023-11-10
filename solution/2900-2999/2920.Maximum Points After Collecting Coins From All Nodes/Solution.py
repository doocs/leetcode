class Solution:
    def maximumPoints(self, edges: List[List[int]], coins: List[int], k: int) -> int:
        @cache
        def dfs(i: int, fa: int, j: int) -> int:
            a = (coins[i] >> j) - k
            b = coins[i] >> (j + 1)
            for c in g[i]:
                if c != fa:
                    a += dfs(c, i, j)
                    if j < 14:
                        b += dfs(c, i, j + 1)
            return max(a, b)

        n = len(coins)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = dfs(0, -1, 0)
        dfs.cache_clear()
        return ans
