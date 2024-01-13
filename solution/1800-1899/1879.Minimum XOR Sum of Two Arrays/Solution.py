class Solution:
    def minimumXORSum(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums2)
        f = [[inf] * (1 << n) for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(nums1, 1):
            for j in range(1 << n):
                for k in range(n):
                    if j >> k & 1:
                        f[i][j] = min(f[i][j], f[i - 1][j ^ (1 << k)] + (x ^ nums2[k]))
        return f[-1][-1]
