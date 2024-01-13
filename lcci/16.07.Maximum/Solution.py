class Solution:
    def maximum(self, a: int, b: int) -> int:
        k = (int(((a - b) & 0xFFFFFFFFFFFFFFFF) >> 63)) & 1
        return a * (k ^ 1) + b * k
