class Solution:
    def minElement(self, nums: List[int]) -> int:
        return min(sum(int(b) for b in str(x)) for x in nums)
