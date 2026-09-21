class Node:
    __slots__ = "l", "r", "prod", "cnt"

    def __init__(self, l: int, r: int, k: int):
        self.l = l
        self.r = r
        self.prod = 1
        self.cnt = [0] * k


class SegmentTree:
    __slots__ = "k", "tr"

    def __init__(self, nums: list[int], k: int):
        self.k = k
        n = len(nums)
        self.tr = [None] * (n << 2)
        self.build(1, 1, n, nums)

    def merge(self, a: Node, b: Node) -> tuple[int, list[int]]:
        k = self.k
        prod = a.prod * b.prod % k
        cnt = a.cnt[:]
        for r, c in enumerate(b.cnt):
            cnt[a.prod * r % k] += c
        return prod, cnt

    def pushup(self, u: int):
        prod, cnt = self.merge(self.tr[u << 1], self.tr[u << 1 | 1])
        self.tr[u].prod = prod
        self.tr[u].cnt = cnt

    def build(self, u: int, l: int, r: int, nums: list[int]):
        self.tr[u] = Node(l, r, self.k)
        if l == r:
            v = nums[l - 1] % self.k
            self.tr[u].prod = v
            self.tr[u].cnt[v] = 1
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid, nums)
        self.build(u << 1 | 1, mid + 1, r, nums)
        self.pushup(u)

    def modify(self, u: int, x: int, v: int):
        if self.tr[u].l == self.tr[u].r:
            v %= self.k
            self.tr[u].prod = v
            self.tr[u].cnt = [0] * self.k
            self.tr[u].cnt[v] = 1
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u: int, l: int, r: int) -> Node:
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u]
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        left = self.query(u << 1, l, r)
        right = self.query(u << 1 | 1, l, r)
        prod, cnt = self.merge(left, right)
        res = Node(0, 0, self.k)
        res.prod = prod
        res.cnt = cnt
        return res


class Solution:
    def resultArray(
        self, nums: list[int], k: int, queries: list[list[int]]
    ) -> list[int]:
        n = len(nums)
        tree = SegmentTree(nums, k)
        ans = []
        for idx, val, start, x in queries:
            tree.modify(1, idx + 1, val)
            ans.append(tree.query(1, start + 1, n).cnt[x])
        return ans
