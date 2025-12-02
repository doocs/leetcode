class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        n = len(points)

        # cnt1: k -> (b -> count)
        cnt1: dict[float, dict[float, int]] = defaultdict(lambda: defaultdict(int))
        # cnt2: p -> (k -> count)
        cnt2: dict[int, dict[float, int]] = defaultdict(lambda: defaultdict(int))

        for i in range(n):
            x1, y1 = points[i]
            for j in range(i):
                x2, y2 = points[j]
                dx, dy = x2 - x1, y2 - y1

                if dx == 0:
                    k = 1e9
                    b = x1
                else:
                    k = dy / dx
                    b = (y1 * dx - x1 * dy) / dx

                cnt1[k][b] += 1

                p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000)
                cnt2[p][k] += 1

        ans = 0

        for e in cnt1.values():
            s = 0
            for t in e.values():
                ans += s * t
                s += t

        for e in cnt2.values():
            s = 0
            for t in e.values():
                ans -= s * t
                s += t

        return ans
