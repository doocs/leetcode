class Solution:
    def countSequences(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i: int, p: int, q: int) -> int:
            if i == n:
                return 1 if p == k and q == 1 else 0
            x = nums[i]
            res = dfs(i + 1, p, q)
            g = gcd(p * x, q)
            res += dfs(i + 1, p * x // g, q // g)
            g = gcd(p, q * x)
            res += dfs(i + 1, p // g, q * x // g)
            return res

        n = len(nums)
        ans = dfs(0, 1, 1)
        dfs.cache_clear()
        return ans
