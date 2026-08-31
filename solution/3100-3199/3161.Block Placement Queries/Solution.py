class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def getResults(self, queries: List[List[int]]) -> List[bool]:
        m = max(q[1] for q in queries)
        sl = SortedList([0, m + 1])
        for q in queries:
            if q[0] == 1:
                sl.add(q[1])
        tree = BinaryIndexedTree(m + 1)
        for x1, x2 in pairwise(sl):
            tree.update(x2, x2 - x1)
        ans = []
        for q in reversed(queries):
            x = q[1]
            if q[0] == 1:
                i = sl.index(x)
                tree.update(sl[i + 1], sl[i + 1] - sl[i - 1])
                sl.remove(x)
            else:
                i = sl.bisect_right(x)
                pre = sl[i - 1]
                ans.append(tree.query(pre) >= q[2] or x - pre >= q[2])
        return ans[::-1]
