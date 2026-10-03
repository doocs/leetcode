class Solution:
    def validSubarraySplit(self, nums: List[int]) -> int:
        n = len(nums)
        f = [inf] * (n + 1)
        f[n] = 0
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                if gcd(nums[i], nums[j]) > 1:
                    f[i] = min(f[i], 1 + f[j + 1])
        return f[0] if f[0] < inf else -1
