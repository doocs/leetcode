class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        ans = 0
        for i, a in enumerate(nums):
            for b in nums[i + 1 :]:
                ans = max(ans, (a - 1) * (b - 1))
        return ans
