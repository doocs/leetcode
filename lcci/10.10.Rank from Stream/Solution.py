class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int) -> None:
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class StreamRank:

    def __init__(self):
        self.tree = BinaryIndexedTree(50010)

    def track(self, x: int) -> None:
        self.tree.update(x + 1, 1)

    def getRankOfNumber(self, x: int) -> int:
        return self.tree.query(x + 1)


# Your StreamRank object will be instantiated and called as such:
# obj = StreamRank()
# obj.track(x)
# param_2 = obj.getRankOfNumber(x)
