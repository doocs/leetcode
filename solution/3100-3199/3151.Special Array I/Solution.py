class Solution:
    def isArraySpecial(self, nums: List[int]) -> bool:
        return all(a % 2 != b % 2 for a, b in pairwise(nums))
