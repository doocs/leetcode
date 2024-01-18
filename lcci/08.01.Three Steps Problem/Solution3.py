import numpy as np


class Solution:
    def waysToStep(self, n: int) -> int:
        if n < 4:
            return 2 ** (n - 1)
        mod = 10**9 + 7
        factor = np.mat([(1, 1, 0), (1, 0, 1), (1, 0, 0)], np.dtype("O"))
        res = np.mat([(4, 2, 1)], np.dtype("O"))
        n -= 4
        while n:
            if n & 1:
                res = res * factor % mod
            factor = factor * factor % mod
            n >>= 1
        return res.sum() % mod
