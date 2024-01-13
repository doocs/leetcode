class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        @cache
        def dfs(pos, mask, lead):
            if pos <= 0:
                return 1
            ans = 0
            for i in range(10):
                if (mask >> i) & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead)
                else:
                    ans += dfs(pos - 1, mask | (1 << i), False)
            return ans

        return dfs(n, 0, True)
