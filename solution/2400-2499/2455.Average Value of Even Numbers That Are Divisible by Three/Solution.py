class Solution:
    def averageValue(self, nums: list[int]) -> int:
        s = n = 0
        for x in nums:
            if x % 6 == 0:
                s += x
                n += 1
        return 0 if n == 0 else s // n
