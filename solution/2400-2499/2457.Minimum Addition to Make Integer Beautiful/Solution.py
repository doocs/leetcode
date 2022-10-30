class Solution:
    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def f(x):
            v = 0
            while x:
                v += x % 10
                x //= 10
            return v

        x = 0
        while f(n + x) > target:
            y = n + x
            p = 10
            while y % 10 == 0:
                y //= 10
                p *= 10
            x = (y // 10 + 1) * p - n
        return x
