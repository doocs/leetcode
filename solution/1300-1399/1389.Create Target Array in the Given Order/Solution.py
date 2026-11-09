class Solution:
    def createTargetArray(self, nums: list[int], index: list[int]) -> list[int]:
        target = []
        for x, i in zip(nums, index):
            target.insert(i, x)
        return target
