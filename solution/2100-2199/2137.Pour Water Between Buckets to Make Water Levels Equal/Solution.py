class Solution:
    def equalizeWater(self, buckets: List[int], loss: int) -> float:
        def check(v):
            a = b = 0
            for x in buckets:
                if x >= v:
                    a += x - v
                else:
                    b += (v - x) * 100 / (100 - loss)
            return a >= b

        l, r = 0, max(buckets)
        while r - l > 1e-5:
            mid = (l + r) / 2
            if check(mid):
                l = mid
            else:
                r = mid
        return l
