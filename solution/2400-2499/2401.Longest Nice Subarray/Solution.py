class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = j = mask = 0
        for i, x in enumerate(nums):
            while mask & x:
                mask ^= nums[j]
                j += 1
            ans = max(ans, i - j + 1)
            mask |= x
        return ans
