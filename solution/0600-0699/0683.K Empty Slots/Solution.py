class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        vis = [False] * (n + 1)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            vis[x] = True
            y = x - k - 1
            if y > 0 and vis[y] and tree.query(x - 1) - tree.query(y) == 0:
                return i
            y = x + k + 1
            if y <= n and vis[y] and tree.query(y - 1) - tree.query(x) == 0:
                return i
        return -1
