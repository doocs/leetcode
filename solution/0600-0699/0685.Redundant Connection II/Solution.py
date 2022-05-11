class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.n = n

    def union(self, a, b):
        if self.find(a) == self.find(b):
            return False
        self.p[self.find(a)] = self.find(b)
        self.n -= 1
        return True

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]


class Solution:
    def findRedundantDirectedConnection(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        p = list(range(n + 1))
        uf = UnionFind(n + 1)
        conflict = cycle = None
        for i, (u, v) in enumerate(edges):
            if p[v] != v:
                conflict = i
            else:
                p[v] = u
                if not uf.union(u, v):
                    cycle = i
        if conflict is None:
            return edges[cycle]
        v = edges[conflict][1]
        if cycle is not None:
            return [p[v], v]
        return edges[conflict]
