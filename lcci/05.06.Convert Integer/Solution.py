class Solution:
    def convertInteger(self, A: int, B: int) -> int:
        A &= 0xFFFFFFFF
        B &= 0xFFFFFFFF
        return (A ^ B).bit_count()
