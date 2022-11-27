class Solution:
    def check(self, nums: List[int]) -> bool:
        return sum(nums[i - 1] > v for i, v in enumerate(nums)) <= 1
