class PersistentUnionFind:
    def __init__(self, n):
        self.rank = [0] * n
        self.p = list(range(n))
        self.version = [inf] * n

    def find(self, x, t=inf):
        if self.p[x] == x or self.version[x] >= t:
            return x
        return self.find(self.p[x], t)

    def union(self, a, b, t):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.rank[pa] > self.rank[pb]:
            self.version[pb] = t
            self.p[pb] = pa
        else:
            self.version[pa] = t
            self.p[pa] = pb
            if self.rank[pa] == self.rank[pb]:
                self.rank[pb] += 1
        return True


class DistanceLimitedPathsExist:
    def __init__(self, n: int, edgeList: List[List[int]]):
        self.puf = PersistentUnionFind(n)
        edgeList.sort(key=lambda x: x[2])
        for u, v, dis in edgeList:
            self.puf.union(u, v, dis)

    def query(self, p: int, q: int, limit: int) -> bool:
        return self.puf.find(p, limit) == self.puf.find(q, limit)
