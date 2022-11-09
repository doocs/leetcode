class Solution:
    def minimumIncompatibility(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(mask):
            if mask == (1 << n) - 1:
                return 0
            d = {v: i for i, v in enumerate(nums) if (mask >> i & 1) == 0}
            ans = inf
            if len(d) < m:
                return ans
            for vs in combinations(d.keys(), m):
                nxt = mask
                for v in vs:
                    nxt |= 1 << d[v]
                ans = min(ans, max(vs) - min(vs) + dfs(nxt))
            return ans

        n = len(nums)
        m = n // k
        ans = dfs(0)
        dfs.cache_clear()
        return ans if ans < inf else -1
