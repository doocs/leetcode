class Solution:
    def minSum(self, nums1: List[int], nums2: List[int]) -> int:
        s1 = sum(nums1) + nums1.count(0)
        s2 = sum(nums2) + nums2.count(0)
        if s1 > s2:
            return self.minSum(nums2, nums1)
        if s1 == s2:
            return s1
        return -1 if nums1.count(0) == 0 else s2
