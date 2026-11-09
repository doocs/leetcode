class Solution:
    def returnToBoundaryCount(self, nums: list[int]) -> int:
        return sum(s == 0 for s in accumulate(nums))
