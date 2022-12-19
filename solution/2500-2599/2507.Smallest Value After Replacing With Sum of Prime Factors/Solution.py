class Solution:
    def smallestValue(self, n: int) -> int:
        while 1:
            t, s, i = n, 0, 2
            while i <= n // i:
                while n % i == 0:
                    n //= i
                    s += i
                i += 1
            if n > 1:
                s += n
            if s == t:
                return t
            n = s
