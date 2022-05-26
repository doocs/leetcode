class Solution:
    def findNthDigit(self, n: int) -> int:
        bits, t = 1, 9
        while n > bits * t:
            n -= bits * t
            bits += 1
            t *= 10

        start = 10 ** (bits - 1) + (n // bits) - 1
        if n % bits == 0:
            return start % 10
        return int(str((start + 1))[(n % bits) - 1])
