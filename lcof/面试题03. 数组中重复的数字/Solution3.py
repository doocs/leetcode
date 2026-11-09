class Solution:
    def findRepeatNumber(self, nums: list[int]) -> int:
        for i, v in enumerate(nums):
            while v != i:
                if nums[v] == v:
                    return v
                nums[i], nums[v] = nums[v], nums[i]
                v = nums[i]
