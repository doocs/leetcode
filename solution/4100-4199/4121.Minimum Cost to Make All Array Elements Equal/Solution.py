class Solution:
    def minCost(self, nums: list[int]) -> int:
        n = len(nums)
        ans = inf
        l = 0
        for r, x in enumerate(nums):
            if r == n - 1 or x != nums[r + 1]:
                if r > 0 and l < n - 1:
                    ans = min(ans, (l + n - r - 1) * x)
                l = r + 1
        return ans
