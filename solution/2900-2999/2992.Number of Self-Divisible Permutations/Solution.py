class Solution:
    def selfDivisiblePermutationCount(self, n: int) -> int:
        @cache
        def dfs(mask: int) -> int:
            i = mask.bit_count() + 1
            if i > n:
                return 1
            ans = 0
            for j in range(1, n + 1):
                if (mask >> j & 1) == 0 and (i % j == 0 or j % i == 0):
                    ans += dfs(mask | 1 << j)
            return ans

        return dfs(0)
