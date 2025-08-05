class SegmentTree:
    __slots__ = ["nums", "tr"]

    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [0] * (n << 2)
        self.build(1, 1, n)

    def build(self, u, l, r):
        if l == r:
            self.tr[u] = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, l, r, i, v):
        if l == r:
            self.tr[u] = v
            return
        mid = (l + r) >> 1
        if i <= mid:
            self.modify(u << 1, l, mid, i, v)
        else:
            self.modify(u << 1 | 1, mid + 1, r, i, v)
        self.pushup(u)

    def query(self, u, l, r, v):
        if self.tr[u] < v:
            return -1
        if l == r:
            return l
        mid = (l + r) >> 1
        if self.tr[u << 1] >= v:
            return self.query(u << 1, l, mid, v)
        return self.query(u << 1 | 1, mid + 1, r, v)

    def pushup(self, u):
        self.tr[u] = max(self.tr[u << 1], self.tr[u << 1 | 1])


class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        tree = SegmentTree(baskets)
        n = len(baskets)
        ans = 0
        for x in fruits:
            i = tree.query(1, 1, n, x)
            if i < 0:
                ans += 1
            else:
                tree.modify(1, 1, n, i, 0)
        return ans
