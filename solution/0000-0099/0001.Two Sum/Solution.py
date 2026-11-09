class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        d = {}
        for i, x in enumerate(nums):
            if (y := target - x) in d:
                return [d[y], i]
            d[x] = i
