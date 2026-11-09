class Solution:
    def addedInteger(self, nums1: list[int], nums2: list[int]) -> int:
        return min(nums2) - min(nums1)
