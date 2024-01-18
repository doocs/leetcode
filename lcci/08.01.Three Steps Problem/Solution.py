class Solution:
    def waysToStep(self, n: int) -> int:
        a, b, c = 1, 2, 4
        mod = 10**9 + 7
        for _ in range(n - 1):
            a, b, c = b, c, (a + b + c) % mod
        return a
