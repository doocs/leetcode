class Solution:
    def peakValleyDifference(self, nums: list[int]) -> int:
        ans = 0
        for i in range(1, len(nums) - 1):
            if nums[i - 1] < nums[i] > nums[i + 1]:
                ans += nums[i]
            elif nums[i - 1] > nums[i] < nums[i + 1]:
                ans -= nums[i]
        return ans
