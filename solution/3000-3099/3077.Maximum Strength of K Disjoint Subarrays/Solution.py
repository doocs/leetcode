class Solution:
    def maximumStrength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[[-inf, -inf] for _ in range(k + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(k + 1):
                sign = 1 if j & 1 else -1
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1])
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + sign * x * (k - j + 1))
                if j:
                    f[i][j][1] = max(
                        f[i][j][1], max(f[i - 1][j - 1]) + sign * x * (k - j + 1)
                    )
        return max(f[n][k])
