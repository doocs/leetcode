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


class Solution:
    def minOperations(self, nums: list[int], locked: list[int], k: int) -> int:
        n = len(nums)
        bit = BinaryIndexedTree(n)
        inv = 0
        for x in reversed(nums):
            inv += bit.query(x - 1)
            bit.update(x, 1)

        cnt = mx = j = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            if mx == i + 1:
                if i > j:
                    cnt += sum(locked[j : i + 1])
                j = i + 1

        return -1 if cnt > k else cnt + inv
