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
    def countRatioSubarrays(self, nums: list[int], a: int, b: int) -> int:
        n = len(nums)
        s = [0] * (n + 1)
        for i, x in enumerate(nums):
            s[i + 1] = s[i] + (a if x % 2 else -b)

        st = sorted(set(s))
        bit = BinaryIndexedTree(len(st) + 1)
        ans = 0
        for v in s:
            x = bisect_left(st, v) + 1
            ans += bit.query(x)
            bit.update(x, 1)
        return ans
