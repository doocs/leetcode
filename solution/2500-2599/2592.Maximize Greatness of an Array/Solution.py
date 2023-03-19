class Solution:
    def maximizeGreatness(self, nums: List[int]) -> int:
        nums.sort()
        i = 0
        for x in nums:
            i += x > nums[i]
        return i
