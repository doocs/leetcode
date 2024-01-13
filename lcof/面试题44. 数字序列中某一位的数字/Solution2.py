class Solution:
    def findNthDigit(self, n: int) -> int:
        if n < 10:
            return n
        n -= 10
        k, p = 2, 10
        while n >= 9 * k * p:
            n -= 9 * k * p
            k += 1
            p *= 10
        x = p + n // k
        return int(str(x)[n % k])
