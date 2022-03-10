from sortedcontainers import SortedDict


class MyCalendar:
    def __init__(self):
        self.sd = SortedDict()

    def book(self, start: int, end: int) -> bool:
        idx = self.sd.bisect_right(start)
        if 0 <= idx < len(self.sd):
            if end > self.sd.values()[idx]:
                return False
        self.sd[end] = start
        return True


# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)
