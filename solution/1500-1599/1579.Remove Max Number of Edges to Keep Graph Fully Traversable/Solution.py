class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.n = n

    def union(self, a, b) -> bool:
        pa, pb = self.find(a - 1), self.find(b - 1)
        if pa == pb:
            return False
        self.p[pa] = pb
        self.n -= 1
        return True

    def find(self, x) -> int:
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]


class Solution:
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        ufa, ufb = UnionFind(n), UnionFind(n)
        res = 0
        for t, u, v in edges:
            if t == 3:
                if not ufa.union(u, v):
                    res += 1
                else:
                    ufb.union(u, v)
        for t, u, v in edges:
            if t == 1:
                if not ufa.union(u, v):
                    res += 1
            elif t == 2:
                if not ufb.union(u, v):
                    res += 1
        return res if ufa.n == 1 and ufb.n == 1 else -1
