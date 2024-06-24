class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(nums):
                return 0
            ans = nums[i] + dfs(i + 1, 1)
            if j == 1:
                ans = max(ans, -nums[i] + dfs(i + 1, 0))
            return ans

        return dfs(0, 0)
