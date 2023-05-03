class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, x in enumerate(nums):
            f[i][i] = x
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1])
        return f[0][n - 1] >= 0
