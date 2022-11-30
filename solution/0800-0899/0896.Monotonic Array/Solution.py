class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        incr = all(a <= b for a, b in pairwise(nums))
        decr = all(a >= b for a, b in pairwise(nums))
        return incr or decr
