class Solution:
    def minInsertions(self, s: str) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= j:
                return 0
            if s[i] == s[j]:
                return dfs(i + 1, j - 1)
            return 1 + min(dfs(i + 1, j), dfs(i, j - 1))

        return dfs(0, len(s) - 1)
