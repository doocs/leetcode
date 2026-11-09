class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        ans = 0
        for i, x in enumerate(nums, 1):
            ans ^= i ^ x
        return ans
