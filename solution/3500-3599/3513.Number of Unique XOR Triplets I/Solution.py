class Solution:
    def uniqueXorTriplets(self, nums: list[int]) -> int:
        n = len(nums)
        return n if n <= 2 else 1 << n.bit_length()
