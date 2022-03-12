class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, val):
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s = max(s, self.c[x])
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def longestObstacleCourseAtEachPosition(self, obstacles: List[int]) -> List[int]:
        s = sorted(set(obstacles))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = []
        for v in obstacles:
            x = m[v]
            ans.append(1 + tree.query(x))
            tree.update(x, ans[-1])
        return ans
