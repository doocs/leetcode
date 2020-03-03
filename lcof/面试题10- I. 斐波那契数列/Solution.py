class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            s = a + b
            a, b = b, s
        return a % 1000000007