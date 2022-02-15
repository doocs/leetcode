class Solution:
    def subArrayRanges(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(n - 1):
            mi = mx = nums[i]
            for j in range(i + 1, n):
                mi = min(mi, nums[j])
                mx = max(mx, nums[j])
                ans += mx - mi
        return ans
