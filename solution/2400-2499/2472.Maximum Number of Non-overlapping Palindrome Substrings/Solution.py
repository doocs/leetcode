class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            ans = dfs(i + 1)
            for j in range(i + k - 1, n):
                if dp[i][j]:
                    ans = max(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        dp = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]
        ans = dfs(0)
        dfs.cache_clear()
        return ans
