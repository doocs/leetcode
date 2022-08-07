class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        @cache
        def dfs(i):
            if i == n:
                return True
            res = False
            if i < n - 1 and nums[i] == nums[i + 1]:
                res = res or dfs(i + 2)
            if i < n - 2 and nums[i] == nums[i + 1] and nums[i + 1] == nums[i + 2]:
                res = res or dfs(i + 3)
            if (
                i < n - 2
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            ):
                res = res or dfs(i + 3)
            return res

        n = len(nums)
        return dfs(0)
