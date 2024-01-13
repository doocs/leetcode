class Solution:
    def countVowelStrings(self, n: int) -> int:
        @cache
        def dfs(i, j):
            return 1 if i >= n else sum(dfs(i + 1, k) for k in range(j, 5))

        return dfs(0, 0)
