class Solution:
    def maxCount(self, m: int, n: int, ops: list[list[int]]) -> int:
        for a, b in ops:
            m = min(m, a)
            n = min(n, b)
        return m * n
