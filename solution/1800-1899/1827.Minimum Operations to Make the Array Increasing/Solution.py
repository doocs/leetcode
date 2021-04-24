class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        pre_max = nums[0]
        times = 0
        for i in range(1, n):
            if nums[i] <= pre_max:
                steps = pre_max - nums[i] + 1
                times += steps
                pre_max = nums[i] + steps
            else:
                pre_max = nums[i]
        return times
