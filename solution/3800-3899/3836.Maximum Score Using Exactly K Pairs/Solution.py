class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], K: int) -> int:
        n, m = len(nums1), len(nums2)
        f = [[[-inf] * (K + 1) for _ in range(m + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i in range(n + 1):
            for j in range(m + 1):
                for k in range(K + 1):
                    if i > 0:
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j][k])
                    if j > 0:
                        f[i][j][k] = max(f[i][j][k], f[i][j - 1][k])
                    if i > 0 and j > 0 and k > 0:
                        f[i][j][k] = max(
                            f[i][j][k],
                            f[i - 1][j - 1][k - 1] + nums1[i - 1] * nums2[j - 1],
                        )
        return f[n][m][K]
