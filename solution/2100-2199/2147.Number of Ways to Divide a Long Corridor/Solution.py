class Solution:
    def numberOfWays(self, corridor: str) -> int:
        @cache
        def dfs(i, cnt):
            if i == n:
                return int(cnt == 2)
            cnt += corridor[i] == 'S'
            if cnt > 2:
                return 0
            ans = dfs(i + 1, cnt)
            if cnt == 2:
                ans += dfs(i + 1, 0)
                ans %= mod
            return ans

        n = len(corridor)
        mod = 10**9 + 7
        ans = dfs(0, 0)
        dfs.cache_clear()
        return ans
