class Solution:
    def multiply(self, A: int, B: int) -> int:
        if B == 1:
            return A
        if B & 1:
            return (self.multiply(A, B >> 1) << 1) + A
        return self.multiply(A, B >> 1) << 1
