class Solution:
    def maximumJumps(self, nums: List[int], target: int) -> int:
        n = len(nums)
        f = [-inf] * n
        f[-1] = 0
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if abs(nums[i] - nums[j]) <= target:
                    f[i] = max(f[i], 1 + f[j])
        return -1 if f[0] < 0 else f[0]
