class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            case1 = (
                x - k - 1 > 0
                and tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                and tree.query(x - 1) - tree.query(x - k - 1) == 0
            )
            case2 = (
                x + k + 1 <= n
                and tree.query(x + k + 1) - tree.query(x + k) == 1
                and tree.query(x + k) - tree.query(x) == 0
            )
            if case1 or case2:
                return i
        return -1
