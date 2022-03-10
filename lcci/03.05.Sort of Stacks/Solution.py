class SortedStack:
    def __init__(self):
        self.s = []

    def push(self, val: int) -> None:
        t = []
        while not self.isEmpty() and self.s[-1] < val:
            t.append(self.s.pop())
        self.s.append(val)
        while len(t) > 0:
            self.s.append(t.pop())

    def pop(self) -> None:
        if not self.isEmpty():
            self.s.pop()

    def peek(self) -> int:
        return -1 if self.isEmpty() else self.s[-1]

    def isEmpty(self) -> bool:
        return len(self.s) == 0
