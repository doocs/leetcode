class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        n = len(points)
        if len(points) < 3:
            return 0
        number = 0
        for i in range(n):
            distance_counter = collections.Counter()
            for j in range(n):
                if i == j:
                    continue
                x1, y1 = points[i][0], points[i][1]
                x2, y2 = points[j][0], points[j][1]
                distance = (x1 - x2) ** 2 + (y1 - y2) ** 2
                distance_counter[distance] += 1
            number += sum([val * (val - 1) for val in distance_counter.values()])
        return number
