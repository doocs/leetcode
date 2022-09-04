class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = j = t = 0
        for i, v in enumerate(nums):
            while t & v:
                t ^= nums[j]
                j += 1
            t |= v
            ans = max(ans, i - j + 1)
        return ans
