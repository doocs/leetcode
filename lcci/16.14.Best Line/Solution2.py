class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        mx = 0
        x = y = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            dup = []
            for j in range(i + 1, n):
                dx, dy = points[j][0] - x1, points[j][1] - y1
                if dx == 0 and dy == 0:
                    dup.append(j)
                    continue
                g = gcd(dx, dy)
                dx //= g
                dy //= g
                if dx < 0 or (dx == 0 and dy < 0):
                    dx, dy = -dx, -dy
                cnt[(dx, dy)].append(j)
            groups = (
                [js + dup for js in cnt.values()] if cnt else ([dup] if dup else [])
            )
            for js in groups:
                c = len(js) + 1
                b = min(js)
                if c > mx or (c == mx and (i, b) < (x, y)):
                    mx = c
                    x, y = i, b
        return [x, y]
