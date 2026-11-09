class Solution:
    def minStartValue(self, nums: list[int]) -> int:
        s = list(accumulate(nums))
        return 1 if min(s) >= 0 else abs(min(s)) + 1
