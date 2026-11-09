class Solution:
    def xorGame(self, nums: list[int]) -> bool:
        return len(nums) % 2 == 0 or reduce(xor, nums) == 0
