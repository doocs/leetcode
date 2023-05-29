class Solution:
    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def f(x: int) -> int:
            y = 0
            while x:
                y += x % 10
                x //= 10
            return y

        x = 0
        while f(n + x) > target:
            y = n + x
            p = 10
            while y % 10 == 0:
                y //= 10
                p *= 10
            x = (y // 10 + 1) * p - n
        return x
