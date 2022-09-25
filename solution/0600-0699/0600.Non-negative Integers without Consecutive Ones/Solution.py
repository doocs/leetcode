class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(pos, pre, limit):
            if pos <= 0:
                return 1
            up = a[pos] if limit else 1
            ans = 0
            for i in range(up + 1):
                if pre == 1 and i == 1:
                    continue
                ans += dfs(pos - 1, i, limit and i == up)
            return ans

        a = [0] * 33
        l = 0
        while n:
            l += 1
            a[l] = n & 1
            n >>= 1
        return dfs(l, 0, True)
