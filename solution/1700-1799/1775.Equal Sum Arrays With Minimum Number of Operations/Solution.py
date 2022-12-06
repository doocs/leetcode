class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        arr = [6 - v for v in nums1] + [v - 1 for v in nums2]
        d = s2 - s1
        for i, v in enumerate(sorted(arr, reverse=True), 1):
            d -= v
            if d <= 0:
                return i
        return -1
