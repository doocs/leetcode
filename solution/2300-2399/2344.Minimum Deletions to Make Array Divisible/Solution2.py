class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = gcd(*numsDivide)
        y = min((v for v in nums if x % v == 0), default=0)
        return sum(v < y for v in nums) if y else -1
