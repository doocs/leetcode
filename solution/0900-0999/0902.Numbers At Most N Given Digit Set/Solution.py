class Solution:
    def atMostNGivenDigitSet(self, digits: List[str], n: int) -> int:
        @cache
        def dfs(pos, lead, limit):
            if pos <= 0:
                return lead == False
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos - 1, lead, limit and i == up)
                elif i in s:
                    ans += dfs(pos - 1, False, limit and i == up)
            return ans

        l = 0
        a = [0] * 12
        s = {int(d) for d in digits}
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, True, True)
