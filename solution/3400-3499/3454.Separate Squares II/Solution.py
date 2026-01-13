class Node:
    __slots__ = ("l", "r", "cnt", "length")

    def __init__(self):
        self.l = self.r = 0
        self.cnt = self.length = 0


class SegmentTree:
    def __init__(self, nums):
        n = len(nums) - 1
        self.nums = nums
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 0, n - 1)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l != r:
            mid = (l + r) >> 1
            self.build(u << 1, l, mid)
            self.build(u << 1 | 1, mid + 1, r)

    def modify(self, u, l, r, k):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            self.tr[u].cnt += k
        else:
            mid = (self.tr[u].l + self.tr[u].r) >> 1
            if l <= mid:
                self.modify(u << 1, l, r, k)
            if r > mid:
                self.modify(u << 1 | 1, l, r, k)
        self.pushup(u)

    def pushup(self, u):
        if self.tr[u].cnt:
            self.tr[u].length = self.nums[self.tr[u].r + 1] - self.nums[self.tr[u].l]
        elif self.tr[u].l == self.tr[u].r:
            self.tr[u].length = 0
        else:
            self.tr[u].length = self.tr[u << 1].length + self.tr[u << 1 | 1].length

    @property
    def length(self):
        return self.tr[1].length


class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        xs = set()
        segs = []
        for x1, y1, l in squares:
            x2, y2 = x1 + l, y1 + l
            xs.update([x1, x2])
            segs.append((y1, x1, x2, 1))
            segs.append((y2, x1, x2, -1))
        segs.sort()
        st = sorted(xs)
        tree = SegmentTree(st)
        d = {x: i for i, x in enumerate(st)}
        area = 0
        y0 = 0
        for y, x1, x2, k in segs:
            area += (y - y0) * tree.length
            tree.modify(1, d[x1], d[x2] - 1, k)
            y0 = y

        target = area / 2
        area = 0
        y0 = 0
        for y, x1, x2, k in segs:
            t = (y - y0) * tree.length
            if area + t >= target:
                return y0 + (target - area) / tree.length
            area += t
            tree.modify(1, d[x1], d[x2] - 1, k)
            y0 = y
        return 0
