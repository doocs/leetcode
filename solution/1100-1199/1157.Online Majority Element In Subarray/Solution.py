class Node:
    def __init__(self):
        self.l = self.r = 0
        self.x = self.cnt = 0


class SegmentTree:
    def __init__(self, nums):
        self.nums = nums
        n = len(nums)
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].x = self.nums[l - 1]
            self.tr[u].cnt = 1
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def query(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].x, self.tr[u].cnt
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        x1, cnt1 = self.query(u << 1, l, r)
        x2, cnt2 = self.query(u << 1 | 1, l, r)
        if x1 == x2:
            return x1, cnt1 + cnt2
        if cnt1 >= cnt2:
            return x1, cnt1 - cnt2
        else:
            return x2, cnt2 - cnt1

    def pushup(self, u):
        if self.tr[u << 1].x == self.tr[u << 1 | 1].x:
            self.tr[u].x = self.tr[u << 1].x
            self.tr[u].cnt = self.tr[u << 1].cnt + self.tr[u << 1 | 1].cnt
        elif self.tr[u << 1].cnt >= self.tr[u << 1 | 1].cnt:
            self.tr[u].x = self.tr[u << 1].x
            self.tr[u].cnt = self.tr[u << 1].cnt - self.tr[u << 1 | 1].cnt
        else:
            self.tr[u].x = self.tr[u << 1 | 1].x
            self.tr[u].cnt = self.tr[u << 1 | 1].cnt - self.tr[u << 1].cnt


class MajorityChecker:
    def __init__(self, arr: List[int]):
        self.tree = SegmentTree(arr)
        self.d = defaultdict(list)
        for i, x in enumerate(arr):
            self.d[x].append(i)

    def query(self, left: int, right: int, threshold: int) -> int:
        x, _ = self.tree.query(1, left + 1, right + 1)
        l = bisect_left(self.d[x], left)
        r = bisect_left(self.d[x], right + 1)
        return x if r - l >= threshold else -1


# Your MajorityChecker object will be instantiated and called as such:
# obj = MajorityChecker(arr)
# param_1 = obj.query(left,right,threshold)
