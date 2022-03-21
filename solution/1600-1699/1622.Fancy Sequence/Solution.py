
MOD = int(1e9 + 7)


class SegmentTree:
    def __init__(self, l, r):
        self.l = l
        self.r = r
        self.v = 0
        self.left = None
        self.right = None
        self.add = 0
        self.mul = 1

    @property
    def mid(self):
        return (self.l + self.r) >> 1

    @property
    def _left(self):
        if self.left is None:
            self.left = SegmentTree(self.l, self.mid)
        return self.left

    @property
    def _right(self):
        if self.right is None:
            self.right = SegmentTree(self.mid + 1, self.r)
        return self.right

    def pushup(self):
        self.v = self._left.v + self._right.v
        self.v %= MOD

    def modify_add(self, l, r, inc):
        if self.l >= l and self.r <= r:
            self.v = (self.v + (self.r - self.l + 1) * inc) % MOD
            self.add += inc
            return
        self.pushdown()
        if l <= self.mid:
            self._left.modify_add(l, r, inc)
        if r > self.mid:
            self._right.modify_add(l, r, inc)
        self.pushup()

    def modify_mul(self, l, r, m):
        if self.l >= l and self.r <= r:
            self.v = (self.v * m) % MOD
            self.add = (self.add * m) % MOD
            self.mul = (self.mul * m) % MOD
            return
        self.pushdown()
        if l <= self.mid:
            self._left.modify_mul(l, r, m)
        if r > self.mid:
            self._right.modify_mul(l, r, m)
        self.pushup()

    def query(self, l, r):
        if self.l >= l and self.r <= r:
            return self.v
        self.pushdown()
        v = 0
        if l <= self.mid:
            v += self._left.query(l, r)
        if r > self.mid:
            v += self._right.query(l, r)
        return v % MOD

    def pushdown(self):
        left, right = self._left, self._right
        if self.add or self.mul != 1:
            left.v = (left.v * self.mul +
                      (left.r - left.l + 1) * self.add) % MOD
            right.v = (right.v * self.mul +
                       (right.r - right.l + 1) * self.add) % MOD
            left.add = (left.add * self.mul + self.add) % MOD
            right.add = (right.add * self.mul + self.add) % MOD
            left.mul = (left.mul * self.mul) % MOD
            right.mul = (right.mul * self.mul) % MOD
            self.add = 0
            self.mul = 1


class Fancy:

    def __init__(self):
        self.n = 0
        self.tree = SegmentTree(0, 10**5 + 1)

    def append(self, val: int) -> None:
        self.n += 1
        self.tree.modify_add(self.n, self.n, val)

    def addAll(self, inc: int) -> None:
        self.tree.modify_add(1, self.n, inc)

    def multAll(self, m: int) -> None:
        self.tree.modify_mul(1, self.n, m)

    def getIndex(self, idx: int) -> int:
        return -1 if idx >= self.n else self.tree.query(idx + 1, idx + 1)


# Your Fancy object will be instantiated and called as such:
# obj = Fancy()
# obj.append(val)
# obj.addAll(inc)
# obj.multAll(m)
# param_4 = obj.getIndex(idx)
