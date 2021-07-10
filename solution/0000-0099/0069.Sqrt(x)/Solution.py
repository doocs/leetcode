class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        left, right = 1, x
        while left < right:
            mid = (left + right + 1) >> 1
            # mid*mid <= x
            if x // mid >= mid:
                left = mid
            else:
                right = mid - 1
        return left