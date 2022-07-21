class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return sum(v.bit_count() for v in nums) + max(0, max(nums).bit_length() - 1)
