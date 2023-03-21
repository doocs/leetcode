class Solution:
    def maxScore(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        s = 0
        for i, x in enumerate(nums):
            s += x
            if s <= 0:
                return i
        return len(nums)
