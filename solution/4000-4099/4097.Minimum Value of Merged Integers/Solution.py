class Solution:
    def minMergedInteger(self, nums: list[int]) -> int:
        return min(int(str(nums[i]) + str(nums[i + 1])) for i in range(0, len(nums), 2))
