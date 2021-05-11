class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        res = 0
        for i in range(len(nums1)):
            l, r = i, len(nums2) - 1
            while l <= r:
                mid = (l + r) >> 1
                if nums2[mid] >= nums1[i]:
                    res = max(res, mid - i)
                    l = mid + 1
                else:
                    r = mid - 1
        return res
