class Solution:
    def maxValidPairSum(self, nums: list[int], k: int) -> int:
        ans = x = 0
        for j in range(k, len(nums)):
            y = nums[j]
            x = max(x, nums[j - k])
            ans = max(ans, x + y)
        return ans
