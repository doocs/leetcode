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
    def minDeletions(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        nums = [0] * n
        bit = BinaryIndexedTree(n)
        for i in range(1, n):
            nums[i] = int(s[i] == s[i - 1])
            if nums[i]:
                bit.update(i + 1, 1)
        ans = []
        for q in queries:
            if q[0] == 1:
                j = q[1]
                delta = (nums[j] ^ 1) - nums[j]
                nums[j] ^= 1
                bit.update(j + 1, delta)
                if j + 1 < n:
                    delta = (nums[j + 1] ^ 1) - nums[j + 1]
                    nums[j + 1] ^= 1
                    bit.update(j + 2, delta)
            else:
                _, l, r = q
                ans.append(bit.query(r + 1) - bit.query(l + 1))
        return ans
