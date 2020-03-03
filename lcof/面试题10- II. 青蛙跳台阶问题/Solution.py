class Solution:
    def numWays(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            s = a + b
            a, b = b, s
        return b % 1000000007