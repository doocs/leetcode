class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.tree = [0] * (n + 1)

    def update(self, i: int, delta: int) -> None:
        i += 1
        while i <= self.n:
            self.tree[i] ^= delta
            i += i & -i

    def query(self, i: int) -> int:
        res = 0
        while i:
            res ^= self.tree[i]
            i -= i & -i
        return res


class Solution:
    def palindromePath(
        self, n: int, edges: list[list[int]], s: str, queries: list[str]
    ) -> list[bool]:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        parent = [-1] * n
        depth = [0] * n
        tin = [0] * n
        tout = [0] * n
        timer = 0

        def dfs(u: int, p: int) -> None:
            nonlocal timer
            parent[u] = p
            tin[u] = timer
            timer += 1
            for v in g[u]:
                if v != p:
                    depth[v] = depth[u] + 1
                    dfs(v, u)
            tout[u] = timer

        dfs(0, -1)

        m = n.bit_length()
        up = [parent[:]]
        up[0][0] = 0
        for j in range(1, m):
            prev = up[-1]
            up.append([prev[prev[i]] for i in range(n)])

        def lca(u: int, v: int) -> int:
            if depth[u] < depth[v]:
                u, v = v, u
            d = depth[u] - depth[v]
            j = 0
            while d:
                if d & 1:
                    u = up[j][u]
                d >>= 1
                j += 1
            if u == v:
                return u
            for j in range(m - 1, -1, -1):
                if up[j][u] != up[j][v]:
                    u = up[j][u]
                    v = up[j][v]
            return parent[u]

        bit = [1 << (ord(c) - ord("a")) for c in s]
        tree = BinaryIndexedTree(n)

        for u in range(n):
            tree.update(tin[u], bit[u])
            tree.update(tout[u], bit[u])

        ans = []
        for q in queries:
            op, a, b = q.split()
            u = int(a)
            if op == "update":
                x = bit[u]
                y = 1 << (ord(b) - ord("a"))
                if x != y:
                    delta = x ^ y
                    tree.update(tin[u], delta)
                    tree.update(tout[u], delta)
                    bit[u] = y
            else:
                v = int(b)
                w = lca(u, v)
                mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w]
                ans.append(mask & (mask - 1) == 0)

        return ans
