class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n
        self.cnt = n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a - 1), self.find(b - 1)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        self.cnt -= 1
        return True


class Solution:
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        ufa = UnionFind(n)
        ufb = UnionFind(n)
        ans = 0
        for t, u, v in edges:
            if t == 3:
                if ufa.union(u, v):
                    ufb.union(u, v)
                else:
                    ans += 1
        for t, u, v in edges:
            if t == 1:
                ans += not ufa.union(u, v)
            if t == 2:
                ans += not ufb.union(u, v)
        return ans if ufa.cnt == 1 and ufb.cnt == 1 else -1
