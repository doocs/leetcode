class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] = max(self.c[x], delta)
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= x & -x
        return s


class Solution:
    def bestSeqAtIndex(self, height: List[int], weight: List[int]) -> int:
        arr = list(zip(height, weight))
        arr.sort(key=lambda x: (x[0], -x[1]))
        alls = sorted({w for _, w in arr})
        m = {w: i for i, w in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 1
        for _, w in arr:
            x = m[w]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
