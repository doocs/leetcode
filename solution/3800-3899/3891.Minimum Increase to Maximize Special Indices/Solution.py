class Solution:
    def minIncrease(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(nums) - 1:
                return 0
            cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i])
            ans = cost + dfs(i + 2, j)
            if j:
                ans = min(ans, dfs(i + 1, 0))
            return ans

        return dfs(1, len(nums) & 1 ^ 1)
