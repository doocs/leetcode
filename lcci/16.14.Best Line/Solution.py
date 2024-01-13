class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                cnt = 2
                for k in range(j + 1, n):
                    x3, y3 = points[k]
                    a = (y2 - y1) * (x3 - x1)
                    b = (y3 - y1) * (x2 - x1)
                    cnt += a == b
                if mx < cnt:
                    mx = cnt
                    x, y = i, j
        return [x, y]
