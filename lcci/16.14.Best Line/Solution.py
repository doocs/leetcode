class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            for j in range(i + 1, n):
                x2, y2 = points[j]
                dx, dy = x2 - x1, y2 - y1
                g = gcd(dx, dy)
                k = (dx // g, dy // g)
                cnt[k].append((i, j))
                if mx < len(cnt[k]) or (mx == len(cnt[k]) and (x, y) > cnt[k][0]):
                    mx = len(cnt[k])
                    x, y = cnt[k][0]
        return [x, y]
