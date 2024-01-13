class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = gcd(*numsDivide)
        nums.sort()
        return next((i for i, v in enumerate(nums) if x % v == 0), -1)
