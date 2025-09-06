class Solution:
    def sortByAbsoluteValue(self, nums: List[int]) -> List[int]:
        return sorted(nums, key=lambda x: abs(x))
