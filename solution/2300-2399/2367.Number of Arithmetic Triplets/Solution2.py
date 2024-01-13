class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        vis = set(nums)
        return sum(x + diff in vis and x + diff * 2 in vis for x in nums)
