class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]
        
        e = []
        for i in range(m):
            for j in range(n):
                if i < m - 1:
                    e.append([abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j])
                if j < n - 1:
                    e.append([abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1])
        e.sort()
        for h, i, j in e:
            p[find(i)] = find(j)
            if find(0) == find(m * n - 1):
                return h
        return 0
