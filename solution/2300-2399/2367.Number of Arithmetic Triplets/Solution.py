class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        s = set(nums)
        return sum(v + diff in s and v + diff + diff in s for v in nums)
