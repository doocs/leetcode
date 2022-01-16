from functools import lru_cache


class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        @lru_cache(None)
        def dfs(u, k):
            if u == dst:
                return 0
            if k <= 0:
                return float('inf')
            k -= 1
            ans = float('inf')
            for v, p in g[u]:
                ans = min(ans, dfs(v, k) + p)
            return ans

        g = defaultdict(list)
        for u, v, p in flights:
            g[u].append((v, p))
        ans = dfs(src, k + 1)
        return -1 if ans >= float('inf') else ans
