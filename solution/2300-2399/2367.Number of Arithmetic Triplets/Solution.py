class Solution:
    def arithmeticTriplets(self, nums: list[int], diff: int) -> int:
        return sum(b - a == diff and c - b == diff for a, b, c in combinations(nums, 3))
