class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def arrive_on_time(speed):
            res = 0
            for i, d in enumerate(dist):
                res += (d / speed) if i == len(dist) - 1 else math.ceil(d / speed)
            return res <= hour

        left, right = 1, 10**7
        while left < right:
            mid = (left + right) >> 1
            if arrive_on_time(mid):
                right = mid
            else:
                left = mid + 1
        return left if arrive_on_time(left) else -1
