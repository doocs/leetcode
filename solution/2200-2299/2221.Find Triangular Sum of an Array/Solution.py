class Solution:
    def triangularSum(self, nums: List[int]) -> int:
        n = len(nums)
        for i in range(n, 0, -1):
            for j in range(i - 1):
                nums[j] = (nums[j] + nums[j + 1]) % 10
        return nums[0]
