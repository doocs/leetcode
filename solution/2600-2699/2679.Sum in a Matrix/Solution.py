class Solution:
    def matrixSum(self, nums: List[List[int]]) -> int:
        for row in nums:
            row.sort()
        n = len(nums[0])
        return sum(max(row[j] for row in nums) for j in range(n))
