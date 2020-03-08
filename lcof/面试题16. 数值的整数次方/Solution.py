class Solution:
    cache = {}
    def myPow(self, x: float, n: int) -> float:
        self.cache = {}
        return self.pow(x, n)

    def pow(self, x, n):
        if self.cache.get('{}-{}'.format(x, n)):
            return self.cache['{}-{}'.format(x, n)]
        if n == 0: return 1
        if n == 1: return x
        if n == -1: return 1 / x

        half = self.pow(x, n // 2)
        self.cache['{}-{}'.format(x, n // 2)] = half
        return half * half * self.pow(x, n % 2)