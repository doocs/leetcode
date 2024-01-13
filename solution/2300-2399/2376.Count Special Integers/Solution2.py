class Solution:
    def countSpecialNumbers(self, n: int) -> int:
        return self.f(n)

    def f(self, n):
        @cache
        def dfs(pos, mask, lead, limit):
            if pos <= 0:
                return lead ^ 1
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if (mask >> i) & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, mask | 1 << i, False, limit and i == up)
            return ans

        a = [0] * 11
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True, True)
