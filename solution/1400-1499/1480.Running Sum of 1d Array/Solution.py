class Solution:
    def runningSum(self, nums: list[int]) -> list[int]:
        return list(accumulate(nums))
