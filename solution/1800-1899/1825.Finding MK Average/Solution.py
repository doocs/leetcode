from sortedcontainers import SortedList


class MKAverage:
    def __init__(self, m: int, k: int):
        self.m = m
        self.k = k
        self.s = 0
        self.q = deque()
        self.lo = SortedList()
        self.mid = SortedList()
        self.hi = SortedList()

    def addElement(self, num: int) -> None:
        if not self.lo or num <= self.lo[-1]:
            self.lo.add(num)
        elif not self.hi or num >= self.hi[0]:
            self.hi.add(num)
        else:
            self.mid.add(num)
            self.s += num
        self.q.append(num)
        if len(self.q) > self.m:
            x = self.q.popleft()
            if x in self.lo:
                self.lo.remove(x)
            elif x in self.hi:
                self.hi.remove(x)
            else:
                self.mid.remove(x)
                self.s -= x
        while len(self.lo) > self.k:
            x = self.lo.pop()
            self.mid.add(x)
            self.s += x
        while len(self.hi) > self.k:
            x = self.hi.pop(0)
            self.mid.add(x)
            self.s += x
        while len(self.lo) < self.k and self.mid:
            x = self.mid.pop(0)
            self.lo.add(x)
            self.s -= x
        while len(self.hi) < self.k and self.mid:
            x = self.mid.pop()
            self.hi.add(x)
            self.s -= x

    def calculateMKAverage(self) -> int:
        return -1 if len(self.q) < self.m else self.s // (self.m - 2 * self.k)


# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()
