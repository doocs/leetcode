import numpy as np

base = [
    (0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
    (0, 0, 0, 0, 0, 0, 1, 0, 1, 0),
    (0, 0, 0, 0, 0, 0, 0, 1, 0, 1),
    (0, 0, 0, 0, 1, 0, 0, 0, 1, 0),
    (1, 0, 0, 1, 0, 0, 0, 0, 0, 1),
    (0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (1, 1, 0, 0, 0, 0, 0, 1, 0, 0),
    (0, 0, 1, 0, 0, 0, 1, 0, 0, 0),
    (0, 1, 0, 1, 0, 0, 0, 0, 0, 0),
    (0, 0, 1, 0, 1, 0, 0, 0, 0, 0),
]


class Solution:
    def knightDialer(self, n: int) -> int:
        factor = np.asmatrix(base, np.dtype("O"))
        res = np.asmatrix([[1] * 10], np.dtype("O"))
        n -= 1
        mod = 10**9 + 7
        while n:
            if n & 1:
                res = res * factor % mod
            factor = factor * factor % mod
            n >>= 1
        return res.sum() % mod
