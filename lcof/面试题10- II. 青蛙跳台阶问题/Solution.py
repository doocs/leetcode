class Solution:
    def numWays(self, n: int) -> int:
        a = b = 1
        for _ in range(n):
            a, b = b, (a + b) % 1000000007
        return a
