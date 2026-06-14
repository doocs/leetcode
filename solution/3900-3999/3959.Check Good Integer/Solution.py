class Solution:
    def checkGoodInteger(self, n: int) -> bool:
        s = 0
        while n:
            n, x = divmod(n, 10)
            s += x * (x - 1)
        return s >= 50
