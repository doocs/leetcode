class Solution:
    def sumSubseqWidths(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        nums.sort()
        ans, p = 0, 1
        for i, v in enumerate(nums):
            ans = (ans + (v - nums[-i - 1]) * p) % mod
            p = (p << 1) % mod
        return ans
