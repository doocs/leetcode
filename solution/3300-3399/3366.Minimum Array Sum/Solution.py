class Solution:
    def minArraySum(self, nums: List[int], d: int, op1: int, op2: int) -> int:
        n = len(nums)
        f = [[[inf] * (op2 + 1) for _ in range(op1 + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(op1 + 1):
                for k in range(op2 + 1):
                    f[i][j][k] = f[i - 1][j][k] + x
                    if j > 0:
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k] + (x + 1) // 2)
                    if k > 0 and x >= d:
                        f[i][j][k] = min(f[i][j][k], f[i - 1][j][k - 1] + (x - d))
                    if j > 0 and k > 0:
                        y = (x + 1) // 2
                        if y >= d:
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j - 1][k - 1] + y - d)
                        if x >= d:
                            f[i][j][k] = min(
                                f[i][j][k], f[i - 1][j - 1][k - 1] + (x - d + 1) // 2
                            )
        ans = inf
        for j in range(op1 + 1):
            for k in range(op2 + 1):
                ans = min(ans, f[n][j][k])
        return ans
