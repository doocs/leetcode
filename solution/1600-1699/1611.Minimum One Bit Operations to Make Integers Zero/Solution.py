class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        if n <= 1:
            return n
        for i in range(64):
            if (n >> i) == 1:
                base = 1 << i
                break
        return 2 * base - 1 - self.minimumOneBitOperations(n - base)
