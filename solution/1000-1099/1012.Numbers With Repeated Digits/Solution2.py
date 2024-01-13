class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        return n - self.f(n)

    def f(self, n: int) -> int:
        @cache
        def dfs(pos: int, mask: int, lead: bool, limit: bool) -> int:
            if pos < 0:
                return int(lead) ^ 1
            up = nums[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if mask >> i & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, mask | 1 << i, False, limit and i == up)
            return ans

        nums = []
        while n:
            nums.append(n % 10)
            n //= 10
        return dfs(len(nums) - 1, 0, True, True)
