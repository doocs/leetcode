MOD = 10**9 + 7


class Node:
    __slots__ = "left", "right", "l", "r", "mid", "v", "add", "mul"

    def __init__(self, l, r):
        self.left = self.right = None
        self.l, self.r = l, r
        self.mid = (l + r) >> 1
        self.v = self.add = 0
        self.mul = 1


class SegmentTree:
    def __init__(self):
        self.root = Node(1, 10**5 + 1)

    def modify(self, l, r, mul, add, node=None):
        if l > r:
            return
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            self.apply(node, mul, add)
            return
        self.pushdown(node)
        if l <= node.mid:
            self.modify(l, r, mul, add, node.left)
        if r > node.mid:
            self.modify(l, r, mul, add, node.right)
        self.pushup(node)

    def query(self, l, r, node=None):
        if l > r:
            return 0
        if node is None:
            node = self.root
        if node.l >= l and node.r <= r:
            return node.v
        self.pushdown(node)
        v = 0
        if l <= node.mid:
            v = (v + self.query(l, r, node.left)) % MOD
        if r > node.mid:
            v = (v + self.query(l, r, node.right)) % MOD
        return v

    def apply(self, node, mul, add):
        node.v = (node.v * mul + (node.r - node.l + 1) * add) % MOD
        node.add = (node.add * mul + add) % MOD
        node.mul = node.mul * mul % MOD

    def pushup(self, node):
        node.v = (node.left.v + node.right.v) % MOD

    def pushdown(self, node):
        if node.left is None:
            node.left = Node(node.l, node.mid)
        if node.right is None:
            node.right = Node(node.mid + 1, node.r)
        if node.add or node.mul != 1:
            self.apply(node.left, node.mul, node.add)
            self.apply(node.right, node.mul, node.add)
            node.add = 0
            node.mul = 1


class Fancy:
    def __init__(self):
        self.n = 0
        self.tree = SegmentTree()

    def append(self, val: int) -> None:
        self.n += 1
        self.tree.modify(self.n, self.n, 1, val)

    def addAll(self, inc: int) -> None:
        self.tree.modify(1, self.n, 1, inc)

    def multAll(self, m: int) -> None:
        self.tree.modify(1, self.n, m, 0)

    def getIndex(self, idx: int) -> int:
        return -1 if idx >= self.n else self.tree.query(idx + 1, idx + 1)
