class Solution:
    def numberOf2sInRange(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 2), limit and i == up)
            return ans

        a = [0] * 12
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True)
