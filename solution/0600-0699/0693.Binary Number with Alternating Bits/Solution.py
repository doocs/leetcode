class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        n = (n ^ (n >> 1)) + 1
        return (n & (n - 1)) == 0
