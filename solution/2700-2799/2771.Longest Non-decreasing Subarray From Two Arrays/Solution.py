class Solution:
    def maxNonDecreasingLength(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        f = g = 1
        ans = 1
        for i in range(1, n):
            ff = gg = 1
            if nums1[i] >= nums1[i - 1]:
                ff = max(ff, f + 1)
            if nums1[i] >= nums2[i - 1]:
                ff = max(ff, g + 1)
            if nums2[i] >= nums1[i - 1]:
                gg = max(gg, f + 1)
            if nums2[i] >= nums2[i - 1]:
                gg = max(gg, g + 1)
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
