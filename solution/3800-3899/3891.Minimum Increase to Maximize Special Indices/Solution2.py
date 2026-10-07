class Solution:
    def minIncrease(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0, 0] for _ in range(n + 1)]
        for i in range(n - 2, 0, -1):
            cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i])
            f[i][0] = cost + f[i + 2][0]
            f[i][1] = min(cost + f[i + 2][1], f[i + 1][0])
        return f[1][n & 1 ^ 1]
