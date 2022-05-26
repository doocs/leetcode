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


class Solution:
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        pos = {v: i for i, v in enumerate(nums2, 1)}
        ans = 0
        n = len(nums1)
        tree = BinaryIndexedTree(n)
        for num in nums1:
            p = pos[num]
            left = tree.query(p)
            right = n - p - (tree.query(n) - tree.query(p))
            ans += left * right
            tree.update(p, 1)
        return ans
