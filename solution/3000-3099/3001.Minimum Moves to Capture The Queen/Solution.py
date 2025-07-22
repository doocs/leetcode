class Solution:
    def minMovesToCaptureTheQueen(
        self, a: int, b: int, c: int, d: int, e: int, f: int
    ) -> int:
        if a == e and (c != a or (d - b) * (d - f) > 0):
            return 1
        if b == f and (d != b or (c - a) * (c - e) > 0):
            return 1
        if c - e == d - f and (a - e != b - f or (a - c) * (a - e) > 0):
            return 1
        if c - e == f - d and (a - e != f - b or (a - c) * (a - e) > 0):
            return 1
        return 2
