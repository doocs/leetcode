class Solution:
    def evenNumberBitwiseORs(self, nums: List[int]) -> int:
        return reduce(or_, (x for x in nums if x % 2 == 0), 0)
