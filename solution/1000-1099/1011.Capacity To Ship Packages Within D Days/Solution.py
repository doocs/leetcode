class Solution:
    def shipWithinDays(self, weights: List[int], D: int) -> int:
        def check(capacity):
            cnt, t = 1, 0
            for w in weights:
                if w > capacity:
                    return False
                if t + w <= capacity:
                    t += w
                else:
                    cnt += 1
                    t = w
            return cnt <= D

        left, right = 1, 25000000
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
