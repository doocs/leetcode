class Solution:
    def digitsCount(self, d: int, low: int, high: int) -> int:
        return self.f(high, d) - self.f(low - 1, d)

    def f(self, n, d):
        @cache
        def dfs(pos, cnt, lead, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos - 1, cnt, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, cnt + (i == d), False, limit and i == up)
            return ans

        a = [0] * 11
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True, True)
