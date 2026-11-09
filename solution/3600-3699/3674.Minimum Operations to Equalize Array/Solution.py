class Solution:
    def minOperations(self, nums: list[int]) -> int:
        return int(any(x != nums[0] for x in nums))
