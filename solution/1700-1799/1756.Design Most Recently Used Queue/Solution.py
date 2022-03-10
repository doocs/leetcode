class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class MRUQueue:
    def __init__(self, n: int):
        self.data = list(range(n + 1))
        self.tree = BinaryIndexedTree(n + 2010)

    def fetch(self, k: int) -> int:
        left, right = 1, len(self.data)
        while left < right:
            mid = (left + right) >> 1
            if mid - self.tree.query(mid) >= k:
                right = mid
            else:
                left = mid + 1
        self.data.append(self.data[left])
        self.tree.update(left, 1)
        return self.data[left]


# Your MRUQueue object will be instantiated and called as such:
# obj = MRUQueue(n)
# param_1 = obj.fetch(k)
