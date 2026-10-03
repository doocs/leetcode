class Node:
    __slots__ = ["children", "v"]

    def __init__(self):
        self.children: List[Node | None] = [None] * 26
        self.v = -1


class Solution:
    def minimumCost(
        self,
        source: str,
        target: str,
        original: List[str],
        changed: List[str],
        cost: List[int],
    ) -> int:
        m = len(cost)
        g = [[inf] * (m << 1) for _ in range(m << 1)]
        for i in range(m << 1):
            g[i][i] = 0
        root = Node()
        idx = 0

        def insert(w: str) -> int:
            node = root
            for c in w:
                i = ord(c) - ord("a")
                if node.children[i] is None:
                    node.children[i] = Node()
                node = node.children[i]
            if node.v < 0:
                nonlocal idx
                node.v = idx
                idx += 1
            return node.v

        for x, y, z in zip(original, changed, cost):
            x = insert(x)
            y = insert(y)
            g[x][y] = min(g[x][y], z)
        for k in range(idx):
            for i in range(idx):
                if g[i][k] >= inf:
                    continue
                for j in range(idx):
                    if g[i][k] + g[k][j] < g[i][j]:
                        g[i][j] = g[i][k] + g[k][j]

        n = len(source)
        f = [inf] * (n + 1)
        f[n] = 0
        for i in range(n - 1, -1, -1):
            res = f[i + 1] if source[i] == target[i] else inf
            p = q = root
            for j in range(i, n):
                p = p.children[ord(source[j]) - ord("a")]
                q = q.children[ord(target[j]) - ord("a")]
                if p is None or q is None:
                    break
                if p.v < 0 or q.v < 0:
                    continue
                res = min(res, f[j + 1] + g[p.v][q.v])
            f[i] = res
        return -1 if f[0] >= inf else f[0]
