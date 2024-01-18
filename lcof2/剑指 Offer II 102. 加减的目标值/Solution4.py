class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        @cache
        def dfs(i, t):
            if i == n:
                if t == target:
                    return 1
                return 0
            return dfs(i + 1, t + nums[i]) + dfs(i + 1, t - nums[i])

        ans, n = 0, len(nums)
        return dfs(0, 0)
