class Node:
    __slots__ = "l", "r", "g"

    def __init__(self, l: int, r: int):
        self.l = l
        self.r = r
        self.g = 0


class SegmentTree:
    __slots__ = "tr"

    def __init__(self, n: int):
        self.tr: list[Node | None] = [None] * (n << 2)
        self.build(1, 1, n)

    def build(self, u: int, l: int, r: int):
        self.tr[u] = Node(l, r)
        if l == r:
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)

    def pushup(self, u: int):
        self.tr[u].g = gcd(self.tr[u << 1].g, self.tr[u << 1 | 1].g)

    def modify(self, u: int, x: int, v: int):
        if self.tr[u].l == self.tr[u].r:
            self.tr[u].g = v
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u: int, l: int, r: int) -> int:
        if l > r:
            return 0
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].g
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        return gcd(self.query(u << 1, l, mid), self.query(u << 1 | 1, mid + 1, r))


class Solution:
    def countGoodSubseq(self, nums: list[int], p: int, queries: list[list[int]]) -> int:
        n = len(nums)
        tree = SegmentTree(n)
        cnt = 0

        for i, x in enumerate(nums, 1):
            if x % p == 0:
                tree.modify(1, i, x)
                cnt += 1

        ans = 0
        for idx, val in queries:
            if nums[idx] % p == 0:
                tree.modify(1, idx + 1, 0)
                cnt -= 1
            if val % p == 0:
                tree.modify(1, idx + 1, val)
                cnt += 1
            nums[idx] = val

            if tree.tr[1].g != p:
                continue

            if cnt < n or n > 6:
                ans += 1
                continue

            for i in range(1, n + 1):
                left_g = tree.query(1, 1, i - 1)
                right_g = tree.query(1, i + 1, n)
                if gcd(left_g, right_g) == p:
                    ans += 1
                    break

        return ans
