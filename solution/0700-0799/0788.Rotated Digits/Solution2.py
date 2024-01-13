class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(pos, ok, limit):
            if pos <= 0:
                return ok
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i in (0, 1, 8):
                    ans += dfs(pos - 1, ok, limit and i == up)
                if i in (2, 5, 6, 9):
                    ans += dfs(pos - 1, 1, limit and i == up)
            return ans

        a = [0] * 6
        l = 1
        while n:
            a[l] = n % 10
            n //= 10
            l += 1
        return dfs(l, 0, True)
