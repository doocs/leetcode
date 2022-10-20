class Solution:
    def check(self, nums: List[int]) -> bool:
        n = len(nums)
        return sum(v > nums[(i + 1) % n] for i, v in enumerate(nums)) <= 1
