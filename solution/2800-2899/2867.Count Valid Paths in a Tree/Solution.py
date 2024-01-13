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


mx = 10**5 + 10
prime = [True] * (mx + 1)
prime[0] = prime[1] = False
for i in range(2, mx + 1):
    if prime[i]:
        for j in range(i * i, mx + 1, i):
            prime[j] = False


class Solution:
    def countPaths(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        uf = UnionFind(n + 1)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
            if prime[u] + prime[v] == 0:
                uf.union(u, v)

        ans = 0
        for i in range(1, n + 1):
            if prime[i]:
                t = 0
                for j in g[i]:
                    if not prime[j]:
                        cnt = uf.size[uf.find(j)]
                        ans += cnt
                        ans += t * cnt
                        t += cnt
        return ans
