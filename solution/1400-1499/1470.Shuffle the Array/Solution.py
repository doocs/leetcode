class Solution:
    def shuffle(self, nums: list[int], n: int) -> list[int]:
        return [x for pair in zip(nums[:n], nums[n:]) for x in pair]
