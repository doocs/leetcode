class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 0
        nums2 = nums2[::-1]
        for i, v in enumerate(nums1):
            j = len(nums2) - bisect_left(nums2, v) - 1
            ans = max(ans, j - i)
        return ans
