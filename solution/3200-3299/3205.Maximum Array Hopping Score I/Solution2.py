class Solution:
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * n
        for j in range(1, n):
            for i in range(j):
                f[j] = max(f[j], f[i] + (j - i) * nums[j])
        return f[n - 1]
