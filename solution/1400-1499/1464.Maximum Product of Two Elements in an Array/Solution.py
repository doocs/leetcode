class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        i = 0 if nums[0] > nums[1] else 1
        j = 1 - i
        for k in range(2, len(nums)):
            if nums[k] > nums[i]:
                j = k
                i, j = j, i
            elif nums[k] > nums[j]:
                j = k
        return (nums[i] - 1) * (nums[j] - 1)
