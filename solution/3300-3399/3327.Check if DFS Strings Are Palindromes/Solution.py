class Hashing:
    __slots__ = ["mod", "h", "p"]

    def __init__(self, s: List[str], base: int, mod: int):
        self.mod = mod
        self.h = [0] * (len(s) + 1)
        self.p = [1] * (len(s) + 1)
        for i in range(1, len(s) + 1):
            self.h[i] = (self.h[i - 1] * base + ord(s[i - 1])) % mod
            self.p[i] = (self.p[i - 1] * base) % mod

    def query(self, l: int, r: int) -> int:
        return (self.h[r] - self.h[l - 1] * self.p[r - l + 1]) % self.mod


class Solution:
    def findAnswer(self, parent: List[int], s: str) -> List[bool]:
        def dfs(i: int):
            l = len(dfsStr) + 1
            for j in g[i]:
                dfs(j)
            dfsStr.append(s[i])
            r = len(dfsStr)
            pos[i] = (l, r)

        n = len(s)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        dfsStr = []
        pos = {}
        dfs(0)

        base, mod = 13331, 998244353
        h1 = Hashing(dfsStr, base, mod)
        h2 = Hashing(dfsStr[::-1], base, mod)
        ans = []
        for i in range(n):
            l, r = pos[i]
            k = r - l + 1
            v1 = h1.query(l, l + k // 2 - 1)
            v2 = h2.query(n - r + 1, n - r + 1 + k // 2 - 1)
            ans.append(v1 == v2)
        return ans
