class Fancy:
    def __init__(self):
        self.mod = 10**9 + 7
        self.nums = []
        self.a = 1
        self.b = 0

    def append(self, val: int) -> None:
        x = (val - self.b) * pow(self.a, self.mod - 2, self.mod) % self.mod
        self.nums.append(x)

    def addAll(self, inc: int) -> None:
        self.b = (self.b + inc) % self.mod

    def multAll(self, m: int) -> None:
        self.a = self.a * m % self.mod
        self.b = self.b * m % self.mod

    def getIndex(self, idx: int) -> int:
        if idx >= len(self.nums):
            return -1
        return (self.a * self.nums[idx] + self.b) % self.mod
