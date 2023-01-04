class Solution:
    def maxValue(self, n: int, index: int, maxSum: int) -> int:
        def sum(x, cnt):
            return (
                (x + x - cnt + 1) * cnt // 2 if x >= cnt else (x + 1) * x // 2 + cnt - x
            )

        left, right = 1, maxSum
        while left < right:
            mid = (left + right + 1) >> 1
            if sum(mid - 1, index) + sum(mid, n - index) <= maxSum:
                left = mid
            else:
                right = mid - 1
        return left
