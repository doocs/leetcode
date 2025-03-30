class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        s = set(nums)
        ans = -1
        for x in nums:
            t = 0
            while x in s:
                x *= x
                t += 1
            if t > 1:
                ans = max(ans, t)
        return ans
