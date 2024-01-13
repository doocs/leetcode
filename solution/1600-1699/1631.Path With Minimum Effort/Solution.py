class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True

    def connected(self, a, b):
        return self.find(a) == self.find(b)


class Solution:
    def minimumEffortPath(self, heights: List[List[int]]) -> int:
        m, n = len(heights), len(heights[0])
        uf = UnionFind(m * n)
        e = []
        dirs = (0, 1, 0)
        for i in range(m):
            for j in range(n):
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        e.append(
                            (abs(heights[i][j] - heights[x][y]), i * n + j, x * n + y)
                        )
        e.sort()
        for h, a, b in e:
            uf.union(a, b)
            if uf.connected(0, m * n - 1):
                return h
        return 0
