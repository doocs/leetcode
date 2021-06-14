class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        low, high = 1, x
        while low < high:
            mid = (low + high + 1) >> 1
            # mid * mid <= x
            if x // mid >= mid:
                low = mid
            else:
                high = mid - 1
        return low
