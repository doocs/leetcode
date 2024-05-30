def max(a: int, b: int) -> int:
    return a if a > b else b


class Node:
    __slots__ = "l", "r", "lmx", "rmx", "mx"

    def __init__(self, l: int, r: int):
        self.l = l
        self.r = r
        self.lmx = self.rmx = self.mx = 1


class SegmentTree:
    __slots__ = "s", "tr"

    def __init__(self, s: str):
        self.s = list(s)
        n = len(s)
        self.tr: List[Node | None] = [None] * (n * 4)
        self.build(1, 1, n)

    def build(self, u: int, l: int, r: int):
        self.tr[u] = Node(l, r)
        if l == r:
            return
        mid = (l + r) // 2
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def query(self, u: int, l: int, r: int) -> int:
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].mx
        mid = (self.tr[u].l + self.tr[u].r) // 2
        ans = 0
        if r <= mid:
            ans = self.query(u << 1, l, r)
        if l > mid:
            ans = max(ans, self.query(u << 1 | 1, l, r))
        return ans

    def modify(self, u: int, x: int, v: str):
        if self.tr[u].l == self.tr[u].r:
            self.s[x - 1] = v
            return
        mid = (self.tr[u].l + self.tr[u].r) // 2
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def pushup(self, u: int):
        root, left, right = self.tr[u], self.tr[u << 1], self.tr[u << 1 | 1]
        root.lmx = left.lmx
        root.rmx = right.rmx
        root.mx = max(left.mx, right.mx)
        a, b = left.r - left.l + 1, right.r - right.l + 1
        if self.s[left.r - 1] == self.s[right.l - 1]:
            if left.lmx == a:
                root.lmx += right.lmx
            if right.rmx == b:
                root.rmx += left.rmx
            root.mx = max(root.mx, left.rmx + right.lmx)


class Solution:
    def longestRepeating(
        self, s: str, queryCharacters: str, queryIndices: List[int]
    ) -> List[int]:
        tree = SegmentTree(s)
        ans = []
        for x, v in zip(queryIndices, queryCharacters):
            tree.modify(1, x + 1, v)
            ans.append(tree.query(1, 1, len(s)))
        return ans
