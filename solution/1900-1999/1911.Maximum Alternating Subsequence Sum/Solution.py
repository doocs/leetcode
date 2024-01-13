class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            f[i] = max(g[i - 1] - x, f[i - 1])
            g[i] = max(f[i - 1] + x, g[i - 1])
        return max(f[n], g[n])
