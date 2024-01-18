class Solution:
    def lastRemaining(self, n: int, m: int) -> int:
        def f(n, m):
            if n == 1:
                return 0
            x = f(n - 1, m)
            return (m + x) % n

        return f(n, m)
