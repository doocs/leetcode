class Solution:
    def finalElement(self, nums: List[int]) -> int:
        return max(nums[0], nums[-1])
