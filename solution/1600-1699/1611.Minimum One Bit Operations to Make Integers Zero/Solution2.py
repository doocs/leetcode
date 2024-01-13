class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        if n == 0:
            return 0
        return n ^ self.minimumOneBitOperations(n >> 1)
