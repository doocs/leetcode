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
    def distantSubarrays(self, nums: list[int], goal: int, k: int) -> int:
        s = list(accumulate(nums, initial=0))
        st = sorted(s)
        n = len(nums)
        ans = (1 + n) * n // 2
        bit = BinaryIndexedTree(len(st) + 1)
        for v in s:
            a = v - goal - k + 1
            b = v - goal + k - 1

            l = bisect_left(st, a) + 1
            r = bisect_left(st, b + 1)
            if l <= r:
                ans -= bit.query(r) - bit.query(l - 1)
            bit.update(bisect_left(st, v) + 1, 1)
        return ans
