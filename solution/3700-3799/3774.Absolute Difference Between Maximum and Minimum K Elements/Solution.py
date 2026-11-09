class Solution:
    def absDifference(self, nums: list[int], k: int) -> int:
        nums.sort()
        return sum(nums[-k:]) - sum(nums[:k])
