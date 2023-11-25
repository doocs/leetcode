class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n: int):
        self.n = n
        self.c = [inf] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = min(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mi = inf
        while x:
            mi = min(mi, self.c[x])
            x -= x & -x
        return -1 if mi == inf else mi


class Solution:
    def leftmostBuildingQueries(
        self, heights: List[int], queries: List[List[int]]
    ) -> List[int]:
        n, m = len(heights), len(queries)
        for i in range(m):
            queries[i] = [min(queries[i]), max(queries[i])]
        j = n - 1
        s = sorted(set(heights))
        ans = [-1] * m
        tree = BinaryIndexedTree(n)
        for i in sorted(range(m), key=lambda i: -queries[i][1]):
            l, r = queries[i]
            while j > r:
                k = n - bisect_left(s, heights[j]) + 1
                tree.update(k, j)
                j -= 1
            if l == r or heights[l] < heights[r]:
                ans[i] = r
            else:
                k = n - bisect_left(s, heights[l])
                ans[i] = tree.query(k)
        return ans
