from sortedcontainers import SortedDict


class MyCalendarTwo:

    def __init__(self):
        self.sd = SortedDict()

    def book(self, start: int, end: int) -> bool:
        if start not in self.sd:
            self.sd[start] = 0
        if end not in self.sd:
            self.sd[end] = 0
        self.sd[start] += 1
        self.sd[end] -= 1
        s = 0
        for v in self.sd.values():
            s += v
            if s > 2:
                self.sd[start] -= 1
                self.sd[end] += 1
                return False
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(start,end)
