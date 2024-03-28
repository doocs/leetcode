# Definition of commonBits API.
# def commonBits(num: int) -> int:


class Solution:
    def findNumber(self) -> int:
        n = 0
        for i in range(32):
            count1 = commonBits(1 << i)
            count2 = commonBits(1 << i)
            if count1 > count2:
                n |= 1 << i
        return n
