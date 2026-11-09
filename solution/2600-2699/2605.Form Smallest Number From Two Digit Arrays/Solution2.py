class Solution:
    def minNumber(self, nums1: list[int], nums2: list[int]) -> int:
        s = set(nums1) & set(nums2)
        if s:
            return min(s)
        a, b = min(nums1), min(nums2)
        return min(a * 10 + b, b * 10 + a)
