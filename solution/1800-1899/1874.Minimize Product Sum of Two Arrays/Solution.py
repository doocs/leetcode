class Solution:
    def minProductSum(self, nums1: list[int], nums2: list[int]) -> int:
        nums1.sort()
        nums2.sort(reverse=True)
        return sum(x * y for x, y in zip(nums1, nums2))
