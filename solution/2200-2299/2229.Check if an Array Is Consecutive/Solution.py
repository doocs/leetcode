class Solution:
    def isConsecutive(self, nums: List[int]) -> bool:
        mi, mx = min(nums), max(nums)
        n = len(nums)
        return len(set(nums)) == n and mx == mi + n - 1
