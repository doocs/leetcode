class Node:
    def __init__(self):
        self.l = self.r = 0
        self.s = self.lazy = 0


class SegmentTree:
    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].s = self.nums[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            self.tr[u].lazy ^= 1
            self.tr[u].s = self.tr[u].r - self.tr[u].l + 1 - self.tr[u].s
            return
        self.pushdown(u)
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if l <= mid:
            self.modify(u << 1, l, r)
        if r > mid:
            self.modify(u << 1 | 1, l, r)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].s
        self.pushdown(u)
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        res = 0
        if l <= mid:
            res += self.query(u << 1, l, r)
        if r > mid:
            res += self.query(u << 1 | 1, l, r)
        return res

    def pushup(self, u):
        self.tr[u].s = self.tr[u << 1].s + self.tr[u << 1 | 1].s

    def pushdown(self, u):
        if self.tr[u].lazy:
            mid = (self.tr[u].l + self.tr[u].r) >> 1
            self.tr[u << 1].s = mid - self.tr[u].l + 1 - self.tr[u << 1].s
            self.tr[u << 1].lazy ^= 1
            self.tr[u << 1 | 1].s = self.tr[u].r - mid - self.tr[u << 1 | 1].s
            self.tr[u << 1 | 1].lazy ^= 1
            self.tr[u].lazy ^= 1


class Solution:
    def handleQuery(
        self, nums1: List[int], nums2: List[int], queries: List[List[int]]
    ) -> List[int]:
        tree = SegmentTree(nums1)
        s = sum(nums2)
        ans = []
        for op, a, b in queries:
            if op == 1:
                tree.modify(1, a + 1, b + 1)
            elif op == 2:
                s += a * tree.query(1, 1, len(nums1))
            else:
                ans.append(s)
        return ans
