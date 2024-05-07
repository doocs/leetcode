class Solution:
    def maxStrength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = -inf
        for i in range(1, 1 << n):
            t = 1
            for j in range(n):
                if i & (1 << j):
                    t *= nums[j]
            ans = max(ans, t)
        return ans
