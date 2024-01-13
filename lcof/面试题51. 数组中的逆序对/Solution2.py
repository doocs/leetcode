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
    def reversePairs(self, nums: List[int]) -> int:
        alls = sorted(set(nums))
        m = len(alls)
        tree = BinaryIndexedTree(m)
        ans = 0
        for v in nums[::-1]:
            x = bisect_left(alls, v) + 1
            ans += tree.query(x - 1)
            tree.update(x, 1)
        return ans
