class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        @cache
        def dfs(i, j, x):
            if i >= j:
                return 0
            if s[i] == s[j] and s[i] != x:
                return dfs(i + 1, j - 1, s[i]) + 2
            return max(dfs(i + 1, j, x), dfs(i, j - 1, x))

        ans = dfs(0, len(s) - 1, '')
        dfs.cache_clear()
        return ans
