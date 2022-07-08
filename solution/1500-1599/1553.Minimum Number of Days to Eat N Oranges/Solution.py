class Solution:
    def minDays(self, n: int) -> int:
        @cache
        def dfs(n):
            if n < 2:
                return n
            return 1 + min(n % 2 + dfs(n // 2), n % 3 + dfs(n // 3))

        return dfs(n)
