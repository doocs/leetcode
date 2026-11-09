class Solution:
    def minPairSum(self, nums: list[int]) -> int:
        nums.sort()
        return max(x + nums[-i - 1] for i, x in enumerate(nums[: len(nums) >> 1]))
