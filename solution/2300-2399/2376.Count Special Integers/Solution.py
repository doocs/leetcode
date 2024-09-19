class Solution:
    def countSpecialNumbers(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool, limit: bool) -> int:
            if i >= len(s):
                return int(lead ^ 1)
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if mask >> j & 1:
                    continue
                if lead and j == 0:
                    ans += dfs(i + 1, mask, True, limit and j == up)
                else:
                    ans += dfs(i + 1, mask | 1 << j, False, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True, True)
