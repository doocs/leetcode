class Solution:
    def maxSum(self, nums1: List[int], nums2: List[int]) -> int:
        mod = 10**9 + 7
        m, n = len(nums1), len(nums2)
        i = j = 0
        f = g = 0
        while i < m or j < n:
            if i == m:
                g += nums2[j]
                j += 1
            elif j == n:
                f += nums1[i]
                i += 1
            elif nums1[i] < nums2[j]:
                f += nums1[i]
                i += 1
            elif nums1[i] > nums2[j]:
                g += nums2[j]
                j += 1
            else:
                f = g = max(f, g) + nums1[i]
                i += 1
                j += 1
        return max(f, g) % mod
