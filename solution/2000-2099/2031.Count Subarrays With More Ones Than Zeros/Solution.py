class BinaryIndexedTree:
    def __init__(self, n):
        n += int(1e5 + 1)
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        x += int(1e5 + 1)
        return x & -x

    def update(self, x, delta):
        x += int(1e5 + 1)
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        x += int(1e5 + 1)
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def subarraysWithMoreZerosThanOnes(self, nums: List[int]) -> int:
        n = len(nums)
        s = [0]
        for v in nums:
            s.append(s[-1] + (v or -1))
        tree = BinaryIndexedTree(n + 1)
        MOD = int(1e9 + 7)
        ans = 0
        for v in s:
            ans = (ans + tree.query(v - 1)) % MOD
            tree.update(v, 1)
        return ans
