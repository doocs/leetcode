import numpy as np


class Solution:
    def fib(self, n: int) -> int:
        factor = np.asmatrix([(1, 1), (1, 0)], np.dtype("O"))
        res = np.asmatrix([(1, 0)], np.dtype("O"))
        while n:
            if n & 1:
                res = res * factor
            factor = factor * factor
            n >>= 1
        return res[0, 1]
