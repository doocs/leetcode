class Solution:
    def evenOddBit(self, n: int) -> list[int]:
        mask = 0x5555
        even = (n & mask).bit_count()
        odd = (n & ~mask).bit_count()
        return [even, odd]
