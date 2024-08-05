class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(pos: int, pre: int, limit: bool) -> int:
            if pos == len(s):
                return 1
            up = int(s[pos]) if limit else 1
            ans = 0
            for i in range(up + 1):
                if pre == 1 and i == 1:
                    continue
                ans += dfs(pos + 1, i, limit and i == up)
            return ans

        s = bin(n)[2:]
        return dfs(0, 0, True)
