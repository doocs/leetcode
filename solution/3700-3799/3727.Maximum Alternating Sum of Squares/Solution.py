class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        nums.sort(key=lambda x: x * x)
        n = len(nums)
        s1 = sum(x * x for x in nums[: n // 2])
        s2 = sum(x * x for x in nums[n // 2 :])
        return s2 - s1
