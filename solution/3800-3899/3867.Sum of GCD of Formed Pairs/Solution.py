class Solution:
    def gcdSum(self, nums: list[int]) -> int:
        n = len(nums)
        prefix_gcd = [0] * n
        mx = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            prefix_gcd[i] = gcd(x, mx)
        prefix_gcd.sort()
        return sum(gcd(prefix_gcd[i], prefix_gcd[-i - 1]) for i in range(n // 2))
