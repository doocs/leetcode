class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        sums = sum(nums)
        pre_sum = 0
        for i, v in enumerate(nums):
            if (pre_sum << 1) == sums - v:
                return i
            pre_sum += v
        return -1
