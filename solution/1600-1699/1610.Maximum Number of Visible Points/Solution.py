class Solution:
    def visiblePoints(
        self, points: List[List[int]], angle: int, location: List[int]
    ) -> int:
        v = []
        x, y = location
        same = 0
        for xi, yi in points:
            if xi == x and yi == y:
                same += 1
            else:
                v.append(atan2(yi - y, xi - x))
        v.sort()
        n = len(v)
        v += [deg + 2 * pi for deg in v]
        t = angle * pi / 180
        mx = max((bisect_right(v, v[i] + t) - i for i in range(n)), default=0)
        return mx + same
