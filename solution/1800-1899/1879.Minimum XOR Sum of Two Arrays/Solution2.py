class Solution:
    def minimumXORSum(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums2)
        f = [inf] * (1 << n)
        f[0] = 0
        for x in nums1:
            for j in range((1 << n) - 1, -1, -1):
                for k in range(n):
                    if j >> k & 1:
                        f[j] = min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]))
        return f[-1]
