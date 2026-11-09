class Solution:
    def leftRightDifference(self, nums: list[int]) -> list[int]:
        l, r = 0, sum(nums)
        ans = []
        for x in nums:
            r -= x
            ans.append(abs(l - r))
            l += x
        return ans
