class Solution:
    def isPossibleToSplit(self, nums: list[int]) -> bool:
        return max(Counter(nums).values()) < 3
