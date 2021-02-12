class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        helper = {}
        for i, v in enumerate(nums):
            num = target - v
            if num in helper:
                return [helper[num], i]
            helper[v] = i
