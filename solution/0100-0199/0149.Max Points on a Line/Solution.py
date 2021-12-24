class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        def gcd(a, b) -> int:
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        if n < 3:
            return n
        res = 0
        for i in range(n - 1):
            counter = Counter()
            t_max = duplicate = 0
            for j in range(i + 1, n):
                delta_x = points[i][0] - points[j][0]
                delta_y = points[i][1] - points[j][1]
                if delta_x == 0 and delta_y == 0:
                    duplicate += 1
                    continue
                g = gcd(delta_x, delta_y)
                d_x = delta_x // g
                d_y = delta_y // g
                key = f'{d_x}.{d_y}'
                counter[key] += 1
                t_max = max(t_max, counter[key])
            res = max(res, t_max + duplicate + 1)
        return res
