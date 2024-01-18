class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        if s % 2 != 0:
            return False
        target = s >> 1

        @cache
        def dfs(i, s):
            nonlocal target
            if s > target or i >= len(nums):
                return False
            if s == target:
                return True
            return dfs(i + 1, s) or dfs(i + 1, s + nums[i])

        return dfs(0, 0)
