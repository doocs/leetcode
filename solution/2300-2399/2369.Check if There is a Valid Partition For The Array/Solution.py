class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        @cache
        def dfs(i: int) -> bool:
            if i >= n:
                return True
            a = i + 1 < n and nums[i] == nums[i + 1]
            b = i + 2 < n and nums[i] == nums[i + 1] == nums[i + 2]
            c = (
                i + 2 < n
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            )
            return (a and dfs(i + 2)) or ((b or c) and dfs(i + 3))

        n = len(nums)
        return dfs(0)
