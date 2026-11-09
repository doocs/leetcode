class MedianCost:
    def __init__(self):
        self.l = SortedList()
        self.r = SortedList()
        self.s1 = 0
        self.s2 = 0

    def add(self, x: int):
        self.r.add(x)
        self.s2 += x

        y = self.r.pop(0)
        self.s2 -= y
        self.l.add(y)
        self.s1 += y
        self.adjust()

    def adjust(self):
        while len(self.l) - len(self.r) > 1:
            x = self.l.pop()
            self.s1 -= x
            self.r.add(x)
            self.s2 += x
        while len(self.r) > len(self.l):
            x = self.r.pop(0)
            self.s2 -= x
            self.l.add(x)
            self.s1 += x

    def remove(self, x: int):
        if x in self.l:
            self.l.remove(x)
            self.s1 -= x
        else:
            self.r.remove(x)
            self.s2 -= x
        self.adjust()

    def query(self) -> int:
        mid = self.l[-1]
        return len(self.l) * mid - self.s1 + self.s2 - len(self.r) * mid


class Solution:
    def minOperations(self, nums: list[int], x: int, k: int) -> int:
        n = len(nums)
        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        mc = MedianCost()
        for i, v in enumerate(nums, 1):
            mc.add(v)
            w = mc.query()
            for j in range(k + 1):
                f[i][j] = min(f[i][j], f[i - 1][j])
                if j > 0 and i >= x:
                    f[i][j] = min(f[i][j], f[i - x][j - 1] + w)
            if i >= x:
                mc.remove(nums[i - x])
        return f[n][k]
