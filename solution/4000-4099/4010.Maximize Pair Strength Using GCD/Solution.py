class Solution:
    def maxPairStrength(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                x = nums[i] * nums[j] // gcd(nums[i], nums[j]) ** 2
                ans = max(ans, x)
        return ans
