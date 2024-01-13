import numpy as np


class Solution:
    def countVowelPermutation(self, n: int) -> int:
        mod = 10**9 + 7
        factor = np.mat(
            [
                (0, 1, 0, 0, 0),
                (1, 0, 1, 0, 0),
                (1, 1, 0, 1, 1),
                (0, 0, 1, 0, 1),
                (1, 0, 0, 0, 0),
            ],
            np.dtype("O"),
        )
        res = np.mat([(1, 1, 1, 1, 1)], np.dtype("O"))
        n -= 1
        while n:
            if n & 1:
                res = res * factor % mod
            factor = factor * factor % mod
            n >>= 1
        return res.sum() % mod
