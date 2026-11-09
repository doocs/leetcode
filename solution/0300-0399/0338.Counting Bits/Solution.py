class Solution:
    def countBits(self, n: int) -> list[int]:
        return [i.bit_count() for i in range(n + 1)]
