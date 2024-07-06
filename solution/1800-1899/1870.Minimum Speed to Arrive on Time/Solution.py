class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(v: int) -> bool:
            s = 0
            for i, d in enumerate(dist):
                t = d / v
                s += t if i == len(dist) - 1 else ceil(t)
            return s <= hour

        if len(dist) > ceil(hour):
            return -1
        r = 10**7 + 1
        ans = bisect_left(range(1, r), True, key=check) + 1
        return -1 if ans == r else ans
