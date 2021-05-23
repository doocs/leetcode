class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def arrive_on_time(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour
        if len(dist) - 1 >= hour:
            return -1
        l, r = 1, 10 ** 7
        while l < r:
            m = (l + r) >> 1
            if arrive_on_time(m):
                r = m
            else:
                l = m + 1
        return l
