class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def corpFlightBookings(self, bookings: List[List[int]], n: int) -> List[int]:
        tree = BinaryIndexedTree(n)
        for first, last, seats in bookings:
            tree.update(first, seats)
            tree.update(last + 1, -seats)
        return [tree.query(i + 1) for i in range(n)]
