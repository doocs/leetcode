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
    def countRangeSum(self, nums: List[int], lower: int, upper: int) -> int:
        presum = [0]
        for v in nums:
            presum.append(presum[-1] + v)
        alls = set()
        for s in presum:
            alls.add(s)
            alls.add(s - lower)
            alls.add(s - upper)
        alls = sorted(alls)
        m = {v: i for i, v in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 0
        for s in presum:
            i, j = m[s - upper], m[s - lower]
            ans += tree.query(j) - tree.query(i - 1)
            tree.update(m[s], 1)
        return ans
