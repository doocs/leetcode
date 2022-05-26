class Solution:
    def countPairs(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        d = [nums1[i] - nums2[i] for i in range(n)]
        d.sort()
        return sum(n - bisect_right(d, -v, lo=i + 1) for i, v in enumerate(d))
