class Solution:
    def sumAndMultiply(self, n: int) -> int:
        p = 1
        x = s = 0
        while n:
            n, v = divmod(n, 10)
            if v:
                s += v
                x += p * v
                p *= 10
        return x * s
