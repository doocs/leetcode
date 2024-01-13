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


class Solution:
    def maximumSafenessFactor(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if grid[0][0] or grid[n - 1][n - 1]:
            return 0
        q = deque()
        dist = [[inf] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                if grid[i][j]:
                    q.append((i, j))
                    dist[i][j] = 0
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and dist[x][y] == inf:
                    dist[x][y] = dist[i][j] + 1
                    q.append((x, y))

        q = ((dist[i][j], i, j) for i in range(n) for j in range(n))
        q = sorted(q, reverse=True)
        uf = UnionFind(n * n)
        for d, i, j in q:
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and dist[x][y] >= d:
                    uf.union(i * n + j, x * n + y)
            if uf.find(0) == uf.find(n * n - 1):
                return int(d)
        return 0
