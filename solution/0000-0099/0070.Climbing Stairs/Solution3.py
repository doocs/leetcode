import numpy as np


class Solution:
    def climbStairs(self, n: int) -> int:
        res = np.mat([(1, 1)], np.dtype("O"))
        factor = np.mat([(1, 1), (1, 0)], np.dtype("O"))
        n -= 1
        while n:
            if n & 1:
                res *= factor
            factor *= factor
            n >>= 1
        return res[0, 0]
