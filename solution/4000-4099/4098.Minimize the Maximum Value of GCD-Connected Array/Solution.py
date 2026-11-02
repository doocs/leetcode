class Solution:
    def minimumMaxValue(self, n: int, index: int, value: int) -> int:
        if value == 1:
            return -1
        p = value
        for x in range(2, isqrt(value) + 1):
            if value % x == 0:
                p = x
                break
        if value - p - (index - 1) * 2 <= 0:
            return -1
        if index == n - 1:
            return value
        return value + p + (n - 2 - index) * 2
