class Solution:
    def minElement(self, nums: list[int]) -> int:
        return min(sum(int(b) for b in str(x)) for x in nums)
