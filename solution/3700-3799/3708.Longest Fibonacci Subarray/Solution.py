class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        ans = f = 2
        for i in range(2, n):
            if nums[i] == nums[i - 1] + nums[i - 2]:
                f = f + 1
                ans = max(ans, f)
            else:
                f = 2
        return ans
