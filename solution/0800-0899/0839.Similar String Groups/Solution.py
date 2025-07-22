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
    def numSimilarGroups(self, strs: List[str]) -> int:
        n, m = len(strs), len(strs[0])
        uf = UnionFind(n)
        for i, s in enumerate(strs):
            for j, t in enumerate(strs[:i]):
                if sum(s[k] != t[k] for k in range(m)) <= 2 and uf.union(i, j):
                    n -= 1
        return n
