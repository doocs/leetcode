class Solution:
    def check(self, nums: List[int]) -> bool:
        return sum(v > nums[(i + 1) % len(nums)] for i, v in enumerate(nums)) <= 1
