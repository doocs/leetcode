class Solution:
    def minimumAverage(self, nums: List[int]) -> float:
        nums.sort()
        n = len(nums)
        return min(nums[i] + nums[n - i - 1] for i in range(n // 2)) / 2
