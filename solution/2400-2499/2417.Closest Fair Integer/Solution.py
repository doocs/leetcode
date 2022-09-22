class Solution:
    def closestFair(self, n: int) -> int:
        a = b = k = 0
        t = n
        while t:
            if (t % 10) & 1:
                a += 1
            else:
                b += 1
            t //= 10
            k += 1
        if k & 1:
            x = 10**k
            y = int('1' * (k >> 1) or '0')
            return x + y
        if a == b:
            return n
        return self.closestFair(n + 1)
