class Solution:
    def sumAndMultiply(self, n: int) -> int:
        p = 1
        x = s = 0
        while n:
            v = n % 10
            s += v
            if v:
                x += p * v
                p *= 10
            n //= 10
        return x * s
