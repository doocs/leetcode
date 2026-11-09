class Solution:
    def findGCD(self, nums: list[int]) -> int:
        return gcd(max(nums), min(nums))
