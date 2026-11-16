class Solution:
    def maxDifference(self, nums: list[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(nums):
                return 0
            x = nums[i]
            res = x - dfs(i + 1) if x % 3 == 0 else x + dfs(i + 1)
            if i + 1 < len(nums):
                s = x + nums[i + 1]
                t = s - dfs(i + 2) if s % 3 == 0 else s + dfs(i + 2)
                res = max(res, t)
            return res

        return dfs(0)
