class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans, d = 0, inf
        for x in nums:
            if (y := abs(x)) < d or (y == d and x > ans):
                ans, d = x, y
        return ans
