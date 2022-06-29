class Solution:
    def runningSum(self, nums: List[int]) -> List[int]:
        return list(accumulate(nums))
