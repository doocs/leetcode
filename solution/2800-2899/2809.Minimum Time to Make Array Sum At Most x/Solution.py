class Solution:
    def minimumTime(self, nums1: List[int], nums2: List[int], x: int) -> int:
        n = len(nums1)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i, (a, b) in enumerate(sorted(zip(nums1, nums2), key=lambda z: z[1]), 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j > 0:
                    f[i][j] = max(f[i][j], f[i - 1][j - 1] + a + b * j)
        s1 = sum(nums1)
        s2 = sum(nums2)
        for j in range(n + 1):
            if s1 + s2 * j - f[n][j] <= x:
                return j
        return -1
