class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        return max(x + nums[n - i - 1] for i, x in enumerate(nums[: n >> 1]))
