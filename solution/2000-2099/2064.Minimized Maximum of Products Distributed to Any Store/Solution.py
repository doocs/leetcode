class Solution:
    def minimizedMaximum(self, n: int, quantities: List[int]) -> int:
        left, right = 1, int(1e5)
        while left < right:
            mid = (left + right) >> 1
            s = sum([(q + mid - 1) // mid for q in quantities])
            if s <= n:
                right = mid
            else:
                left = mid + 1
        return left
