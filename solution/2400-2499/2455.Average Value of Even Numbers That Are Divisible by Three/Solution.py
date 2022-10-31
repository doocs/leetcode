class Solution:
    def averageValue(self, nums: List[int]) -> int:
        s = n = 0
        for v in nums:
            if v % 6 == 0:
                s += v
                n += 1
        return 0 if n == 0 else s // n
