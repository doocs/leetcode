class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, val):
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= x & -x
        return s


class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        m = max(ages)
        tree = BinaryIndexedTree(m)
        for score, age in sorted(zip(scores, ages)):
            tree.update(age, score + tree.query(age))
        return tree.query(m)
