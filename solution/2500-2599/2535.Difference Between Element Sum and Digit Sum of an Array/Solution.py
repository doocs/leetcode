class Solution:
    def differenceOfSum(self, nums: List[int]) -> int:
        a, b = sum(nums), 0
        for x in nums:
            while x:
                b += x % 10
                x //= 10
        return abs(a - b)
