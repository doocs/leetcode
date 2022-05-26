class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = []
        n = len(points)
        for i, (x1, y1) in enumerate(points):
            for j in range(i + 1, n):
                x2, y2 = points[j]
                g.append((abs(x1 - x2) + abs(y1 - y2), i, j))
        g.sort()
        p = list(range(n))
        ans = 0
        for cost, i, j in g:
            if find(i) == find(j):
                continue
            p[find(i)] = find(j)
            n -= 1
            ans += cost
            if n == 1:
                return ans
        return 0
