class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(i: int, ok: int, limit: bool) -> int:
            if i >= len(s):
                return ok
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if j in (0, 1, 8):
                    ans += dfs(i + 1, ok, limit and j == up)
                elif j in (2, 5, 6, 9):
                    ans += dfs(i + 1, 1, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True)
