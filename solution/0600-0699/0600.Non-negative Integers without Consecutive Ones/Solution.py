class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(i: int, pre: int, limit: bool) -> int:
            if i < 0:
                return 1
            up = (n >> i & 1) if limit else 1
            ans = 0
            for j in range(up + 1):
                if pre and j:
                    continue
                ans += dfs(i - 1, j, limit and j == up)
            return ans

        return dfs(n.bit_length() - 1, 0, True)
