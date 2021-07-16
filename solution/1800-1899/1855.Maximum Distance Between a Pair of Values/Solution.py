class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        res, n = 0, len(nums2)
        for i, num in enumerate(nums1):
            left, right = i, n - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if nums2[mid] >= num:
                    left = mid
                else:
                    right = mid - 1
            res = max(res, left - i)
        return res
