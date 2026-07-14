class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i < 0:
                return int(j == k)
            return (
                dfs(i - 1, j, k)
                + dfs(i - 1, gcd(nums[i], j), k)
                + dfs(i - 1, j, gcd(nums[i], k))
            ) % mod

        mod = 10**9 + 7
        return (dfs(len(nums) - 1, 0, 0) - 1) % mod
