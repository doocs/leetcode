class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        n = len(points)
        mx = 0
        x, y = 0, 1
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                if x1 == x2 and y1 == y2:
                    continue
                cnt = 0
                a = b = -1
                for k in range(n):
                    x3, y3 = points[k]
                    c1 = (y2 - y1) * (x3 - x1)
                    c2 = (y3 - y1) * (x2 - x1)
                    if c1 == c2:
                        cnt += 1
                        if a < 0:
                            a = k
                        elif b < 0:
                            b = k
                if cnt > mx or (cnt == mx and (a, b) < (x, y)):
                    mx = cnt
                    x, y = a, b
        return [x, y]
