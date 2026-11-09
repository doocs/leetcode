class Solution:
    def findMissingElements(self, nums: list[int]) -> list[int]:
        mn, mx = min(nums), max(nums)
        s = set(nums)
        return [x for x in range(mn + 1, mx) if x not in s]
