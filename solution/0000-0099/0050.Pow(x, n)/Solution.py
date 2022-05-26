class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n < 0:
            return 1 / self.myPow(x, -n)
        y = self.myPow(x, n >> 1)
        return y * y if (n & 1) == 0 else y * y * x
