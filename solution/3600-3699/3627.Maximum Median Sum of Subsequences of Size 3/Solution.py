class Solution:
    def maximumMedianSum(self, nums: List[int]) -> int:
        nums.sort()
        return sum(nums[len(nums) // 3 :: 2])
