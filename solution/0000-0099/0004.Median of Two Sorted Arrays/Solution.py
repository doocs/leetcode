class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def findKth(i, j, k):
            if i >= m:
                return nums2[j + k - 1]
            if j >= n:
                return nums1[i + k - 1]
            if k == 1:
                return min(nums1[i], nums2[j])
            midVal1 = nums1[i + k // 2 - 1] if i + k // 2 - 1 < m else inf
            midVal2 = nums2[j + k // 2 - 1] if j + k // 2 - 1 < n else inf
            if midVal1 < midVal2:
                return findKth(i + k // 2, j, k - k // 2)
            return findKth(i, j + k // 2, k - k // 2)

        m, n = len(nums1), len(nums2)
        left, right = (m + n + 1) // 2, (m + n + 2) // 2
        return (findKth(0, 0, left) + findKth(0, 0, right)) / 2
