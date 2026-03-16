class UnionFind:
    def __init__(self):
        self.p = {}
        self.size = {}

    def find(self, x):
        if x not in self.p:
            self.p[x] = x
            self.size[x] = 1
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
    def maxActivated(self, points: List[List[int]]) -> int:
        uf = UnionFind()
        m = int(3e9)

        for x, y in points:
            uf.union(x, y + m)

        cnt = Counter()
        for x, _ in points:
            cnt[uf.find(x)] += 1

        mx1 = mx2 = 0
        for x in cnt.values():
            if mx1 < x:
                mx2 = mx1
                mx1 = x
            elif mx2 < x:
                mx2 = x
        return mx1 + mx2 + 1
