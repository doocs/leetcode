class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        edges = []
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                edges.append([abs(x1 - x2) + abs(y1 - y2), i, j])
        edges.sort()
        res = 0
        for cost, i, j in edges:
            if find(i) == find(j):
                continue
            p[find(i)] = find(j)
            n -= 1
            res += cost
            if n == 1:
                return res
        return 0
