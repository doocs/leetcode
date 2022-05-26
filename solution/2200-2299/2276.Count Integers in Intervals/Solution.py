class Node:
    def __init__(self):
        self.tag = 0
        self.tot = 0
        self.left = None
        self.right = None

    def update(self, l, r, a, b):
        if self.tag == 1:
            return
        mid = (a + b) >> 1
        if l == a and r == b:
            self.tag = 1
            self.tot = b - a + 1
            return
        if not self.left:
            self.left = Node()
        if not self.right:
            self.right = Node()
        if mid >= l:
            self.left.update(l, min(mid, r), a, mid)
        if mid + 1 <= r:
            self.right.update(max(mid + 1, l), r, mid + 1, b)
        self.tag = 0
        self.tot = self.left.tot + self.right.tot


class CountIntervals:
    def __init__(self):
        self.tree = Node()

    def add(self, left: int, right: int) -> None:
        self.tree.update(left, right, 0, 1000000010)

    def count(self) -> int:
        return self.tree.tot


# Your CountIntervals object will be instantiated and called as such:
# obj = CountIntervals()
# obj.add(left,right)
# param_2 = obj.count()
