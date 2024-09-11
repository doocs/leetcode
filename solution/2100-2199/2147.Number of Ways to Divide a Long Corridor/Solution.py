class Solution:
    def numberOfWays(self, corridor: str) -> int:
        @cache
        def dfs(i: int, k: int) -> int:
            if i >= len(corridor):
                return int(k == 2)
            k += int(corridor[i] == "S")
            if k > 2:
                return 0
            ans = dfs(i + 1, k)
            if k == 2:
                ans = (ans + dfs(i + 1, 0)) % mod
            return ans

        mod = 10**9 + 7
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
