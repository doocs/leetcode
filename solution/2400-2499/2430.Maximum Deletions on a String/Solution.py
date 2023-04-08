class Solution:
    def deleteString(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == n:
                return 0
            ans = 1
            for j in range(1, (n - i) // 2 + 1):
                if s[i : i + j] == s[i + j : i + j + j]:
                    ans = max(ans, 1 + dfs(i + j))
            return ans

        n = len(s)
        return dfs(0)
