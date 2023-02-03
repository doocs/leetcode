class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos < 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 1), limit and i == up)
            return ans

        a = []
        while n:
            a.append(n % 10)
            n //= 10
        return dfs(len(a) - 1, 0, True)
