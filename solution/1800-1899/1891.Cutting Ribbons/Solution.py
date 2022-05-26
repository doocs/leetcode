class Solution:
    def maxLength(self, ribbons: List[int], k: int) -> int:
        low, high = 0, 100000
        while low < high:
            mid = (low + high + 1) >> 1
            cnt = 0
            for ribbon in ribbons:
                cnt += ribbon // mid
            if cnt < k:
                high = mid - 1
            else:
                low = mid
        return low
