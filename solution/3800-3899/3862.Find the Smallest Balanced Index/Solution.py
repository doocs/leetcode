class Solution:
    def smallestBalancedIndex(self, nums: list[int]) -> int:
        s = sum(nums)
        p = 1
        for i in range(len(nums) - 1, -1, -1):
            s -= nums[i]
            if s == p:
                return i
            p *= nums[i]
            if p >= s:
                break
        return -1
