class Solution:
    def minCost(self, nums: list[int], queries: list[list[int]]) -> list[int]:
        n = len(nums)
        s1 = [0] * n
        s2 = [0] * n
        for i in range(1, n):
            c1 = (
                nums[i] - nums[i - 1]
                if i > 1 and nums[i - 1] - nums[i - 2] <= nums[i] - nums[i - 1]
                else 1
            )
            c2 = (
                nums[i] - nums[i - 1]
                if i < n - 1 and nums[i] - nums[i - 1] > nums[i + 1] - nums[i]
                else 1
            )
            s1[i] = s1[i - 1] + c1
            s2[i] = s2[i - 1] + c2
        m = len(queries)
        ans = [0] * m
        for i, (l, r) in enumerate(queries):
            ans[i] = s1[r] - s1[l] if l < r else s2[l] - s2[r]
        return ans
