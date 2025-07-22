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
    def latestDayToCross(self, row: int, col: int, cells: List[List[int]]) -> int:
        mn = len(cells)
        uf = UnionFind(mn + 2)
        s, t = mn, mn + 1
        dirs = (-1, 0, 1, 0, -1)
        g = [[1] * col for _ in range(row)]
        for i in range(mn - 1, -1, -1):
            x, y = cells[i][0] - 1, cells[i][1] - 1
            g[x][y] = 0
            for a, b in pairwise(dirs):
                nx, ny = x + a, y + b
                if 0 <= nx < row and 0 <= ny < col and g[nx][ny] == 0:
                    uf.union(x * col + y, nx * col + ny)
            if x == 0:
                uf.union(y, s)
            if x == row - 1:
                uf.union(x * col + y, t)
            if uf.find(s) == uf.find(t):
                return i
