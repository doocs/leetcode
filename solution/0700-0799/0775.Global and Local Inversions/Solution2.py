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
    def isIdealPermutation(self, nums: List[int]) -> bool:
        n = len(nums)
        tree = BinaryIndexedTree(n)
        cnt = 0
        for i, v in enumerate(nums):
            cnt += i < n - 1 and v > nums[i + 1]
            cnt -= i - tree.query(v)
            if cnt < 0:
                return False
            tree.update(v + 1, 1)
        return True
