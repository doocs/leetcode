class Solution:
    def maxStrength(self, nums: list[int]) -> int:
        ans = -inf
        for i in range(1, 1 << len(nums)):
            t = 1
            for j, x in enumerate(nums):
                if i >> j & 1:
                    t *= x
            ans = max(ans, t)
        return ans
