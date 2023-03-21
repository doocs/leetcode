class Solution:
    def maxLength(self, ribbons: List[int], k: int) -> int:
        left, right = 0, 100000
        while left < right:
            mid = (left + right + 1) >> 1
            cnt = sum(x // mid for x in ribbons)
            if cnt >= k:
                left = mid
            else:
                right = mid - 1
        return left
