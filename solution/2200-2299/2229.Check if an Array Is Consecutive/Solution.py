class Solution:
    def isConsecutive(self, nums: List[int]) -> bool:
        mi, mx = min(nums), max(nums)
        return len(set(nums)) == mx - mi + 1 == len(nums)
