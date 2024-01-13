class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.lmx = 0
        self.rmx = 0
        self.mx = 0
        self.size = 0
        self.lc = None
        self.rc = None


N = 100010
tr = [Node() for _ in range(N << 2)]


class SegmentTree:
    def __init__(self, s):
        n = len(s)
        self.s = s
        self.build(1, 1, n)

    def build(self, u, l, r):
        tr[u].l = l
        tr[u].r = r
        if l == r:
            tr[u].lmx = tr[u].rmx = tr[u].mx = tr[u].size = 1
            tr[u].lc = tr[u].rc = self.s[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, x, v):
        if tr[u].l == x and tr[u].r == x:
            tr[u].lc = tr[u].rc = v
            return
        mid = (tr[u].l + tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u, l, r):
        if tr[u].l >= l and tr[u].r <= r:
            return tr[u]
        mid = (tr[u].l + tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        left, right = self.query(u << 1, l, r), self.query(u << 1 | 1, l, r)
        ans = Node()
        self._pushup(ans, left, right)
        return ans

    def _pushup(self, root, left, right):
        root.lc, root.rc = left.lc, right.rc
        root.size = left.size + right.size

        root.mx = max(left.mx, right.mx)
        root.lmx, root.rmx = left.lmx, right.rmx

        if left.rc == right.lc:
            if left.lmx == left.size:
                root.lmx += right.lmx
            if right.rmx == right.size:
                root.rmx += left.rmx
            root.mx = max(root.mx, left.rmx + right.lmx)

    def pushup(self, u):
        self._pushup(tr[u], tr[u << 1], tr[u << 1 | 1])


class Solution:
    def longestRepeating(
        self, s: str, queryCharacters: str, queryIndices: List[int]
    ) -> List[int]:
        tree = SegmentTree(s)
        k = len(queryIndices)
        ans = []
        for i, c in enumerate(queryCharacters):
            x = queryIndices[i] + 1
            tree.modify(1, x, c)
            ans.append(tree.query(1, 1, len(s)).mx)
        return ans
