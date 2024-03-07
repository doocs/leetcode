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
    def resultArray(self, nums: List[int]) -> List[int]:
        st = sorted(set(nums))
        m = len(st)
        tree1 = BinaryIndexedTree(m + 1)
        tree2 = BinaryIndexedTree(m + 1)
        tree1.update(bisect_left(st, nums[0]) + 1, 1)
        tree2.update(bisect_left(st, nums[1]) + 1, 1)
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        for x in nums[2:]:
            i = bisect_left(st, x) + 1
            a = len(arr1) - tree1.query(i)
            b = len(arr2) - tree2.query(i)
            if a > b:
                arr1.append(x)
                tree1.update(i, 1)
            elif a < b:
                arr2.append(x)
                tree2.update(i, 1)
            elif len(arr1) <= len(arr2):
                arr1.append(x)
                tree1.update(i, 1)
            else:
                arr2.append(x)
                tree2.update(i, 1)
        return arr1 + arr2
