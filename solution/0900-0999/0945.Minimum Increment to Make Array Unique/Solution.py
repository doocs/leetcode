class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        nums.sort()
        ans, y = 0, -1
        for x in nums:
            y = max(y + 1, x)
            ans += y - x
        return ans
