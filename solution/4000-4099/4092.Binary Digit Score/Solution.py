class Solution:
    def calculateScore(self, a: int, b: int) -> int:
        s1 = a.bit_count()
        s2 = b.bit_count()
        return s1 * s2 if s1 >= s2 else s2 // s1
