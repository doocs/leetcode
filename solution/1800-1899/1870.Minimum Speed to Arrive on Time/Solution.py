class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour

        r = 10**7 + 1
        ans = bisect_left(range(1, r), True, key=check) + 1
        return -1 if ans == r else ans
